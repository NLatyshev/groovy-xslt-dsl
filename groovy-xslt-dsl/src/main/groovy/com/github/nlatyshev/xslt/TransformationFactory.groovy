package com.github.nlatyshev.xslt

import groovy.util.slurpersupport.GPathResult
import groovy.xml.XmlUtil
import org.apache.commons.codec.digest.DigestUtils

/**
 * @author nlatyshev 26/02/2016.
 */
abstract class TransformationFactory {

    Map<Entity, List<Closure>> transformations = [:]

    Transformation get(Entity e) {
        new TransformationImpl(transformations[e] ?: [])
    }

    protected static boolean isAsset(dtl) {
        dtl.cas_id || dtl.ins_id
    }

    protected static String md5(String id) {
        DigestUtils.md5Hex(id).toUpperCase()
    }

    protected static String md5(GPathResult id) {
        md5(id.text())
    }

    protected void transform(Entity e, Closure transformation) {
        transformations[e] = (transformations[e] ?: []) << transformation

    }

    protected static void each(GPathResult r, Closure closure) {
        def el = r.iterator()
        while (el.hasNext()) {
            def w = new NodeProxy().wrap(el.next())
            closure.call(w);
        }
    }

    interface Transformation {
        String apply(String xml)
    }

    protected static class TransformationImpl implements Transformation {
        List<Closure> transformations

        TransformationImpl(List<Closure> transformations) {
            this.transformations = transformations
        }

        String apply(String xml) {
            use(TransformExtensions) {
                if (transformations) {
                    def parser = new XmlSlurper()
                    parser.setKeepIgnorableWhitespace(true)
                    def e = parser.parseText(xml)
                    transformations.each { trf -> trf(e) }
                    xml = XmlUtil.serialize(e)
                }
                xml
            }
        }
    }

    static class TransformExtensions {

        static String or(GPathResult self, GPathResult r) {
            self.text() + '|' + r.text()
        }

        static boolean asBoolean(GPathResult self) {
            !self.text().empty
        }

        static String or(String self, GPathResult r) {
            self + '|' + r.text()
        }
    }

    static class NodeProxy extends groovy.util.Proxy {

        public void setProperty(final String property, final Object newValue) {
            if (getAdaptee()[property].isEmpty()) {
                getAdaptee().appendNode(new GroovyShell().evaluate("{it->${property}('${newValue}')}"))
            } else {
                getAdaptee().setProperty(property, newValue)
            }
        }

        public Object getProperty(final String property) {
            getAdaptee().getProperty(property)
        }

        protected void appendNode(Object newValue) {
            getAdaptee().appendNode(newValue)
        }

        protected void replaceNode(Closure newValue) {
            getAdaptee().replaceNode(newValue)
        };

        protected void replaceBody(Object newValue) {
            getAdaptee().replaceBody(newValue)
        }

    }

}
