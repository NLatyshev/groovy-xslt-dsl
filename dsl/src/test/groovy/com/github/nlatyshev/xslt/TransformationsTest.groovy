package com.github.nlatyshev.xslt

import groovy.xml.MarkupBuilder
import org.custommonkey.xmlunit.XMLUnit
import spock.lang.Specification
import static com.github.nlatyshev.xslt.TransformationFactory.*

/**
 * @author nlatyshev on 14.04.2017.
 */
class TransformationsTest extends Specification {

    def transformations = new Transformations()

    def setup() {
        XMLUnit.setIgnoreWhitespace(true)
        XMLUnit.setNormalizeWhitespace(true)
    }

    def "Add or update col_dtl_id concatenating some other fields (depends on asset or not) and applying md5 function" () {
        when:
            def transformed = transformations.get(Entity.COLLATERAL_DETAIL).apply(load('/collateral_detail.xml'))
        then:
            def expected = new StringWriter()
            new MarkupBuilder(expected).entity() {
                col_dtl() {
                    cas_id('cas_id')
                    ins_id('ins_id')
                    col_agrmt_id('col_agrmt_id')
                    col_agrmt_src_nme('col_agrmt_src_nme')
                    prvr_pty_id('prvr_pty_id')
                    prvr_pty_src_nme('prvr_pty_src_nme')
                    col_dtl_id(md5('col_agrmt_id|col_agrmt_src_nme|cas_id'))
                }
                col_dtl() {
                    ins_id('ins_id')
                    col_agrmt_id('col_agrmt_id')
                    col_agrmt_src_nme('col_agrmt_src_nme')
                    prvr_pty_id('prvr_pty_id')
                    prvr_pty_src_nme('prvr_pty_src_nme')
                    col_dtl_id(md5('col_agrmt_id|col_agrmt_src_nme|ins_id'))
                }
                col_dtl() {
                    col_agrmt_id('col_agrmt_id')
                    col_agrmt_src_nme('col_agrmt_src_nme')
                    prvr_pty_id('prvr_pty_id')
                    prvr_pty_src_nme('prvr_pty_src_nme')
                    col_dtl_id('4DA5450126D03EE5F20D56056A94A7CF')
                }
            }
            XMLUnit.compareXML(transformed, expected.toString()).similar()
    }

    def "Add or update col_covg_lnkge_id concatenating some other fields (depends on covg_typ_indctr) and applying md5 function" () {
        when:
            def transformed = transformations.get(Entity.COLLATERAL_COVERAGE).apply(load('/collateral_coverage.xml'))
        then:
            def expected = new StringWriter()
            new MarkupBuilder(expected).entity() {
                col_covg_lnkge() {
                    covg_typ_indctr('S')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('cont_src_nme|cont_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('P')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('cpty_pty_src_nme|cpty_pty_id|grc_pdt'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('L')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('fac_lmt_src_nme|fac_lmt_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('F')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('fac_agrmt_src_nme|fac_agrmt_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('M')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('legl_agrmt_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('C')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('cpty_pty_src_nme|cpty_pty_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('LC')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('cont_src_nme|cont_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('MC')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5('legl_agrmt_id|abs_clo_tx_id'))
                }
                col_covg_lnkge() {
                    covg_typ_indctr('OTHER')
                    cont_id('cont_id')
                    cont_src_nme('cont_src_nme')
                    cpty_pty_src_nme('cpty_pty_src_nme')
                    cpty_pty_id('cpty_pty_id')
                    grc_pdt('grc_pdt')
                    fac_lmt_src_nme('fac_lmt_src_nme')
                    fac_lmt_id('fac_lmt_id')
                    fac_agrmt_src_nme('fac_agrmt_src_nme')
                    fac_agrmt_id('fac_agrmt_id')
                    legl_agrmt_id('legl_agrmt_id')
                    abs_clo_tx_id('abs_clo_tx_id')
                    col_covg_lnkge_id(md5(''))
                }
            }
            XMLUnit.compareXML(transformed, expected.toString()).similar()
        }

    private String load(filePath) {
        getClass().getResource(filePath).text
    }
}
