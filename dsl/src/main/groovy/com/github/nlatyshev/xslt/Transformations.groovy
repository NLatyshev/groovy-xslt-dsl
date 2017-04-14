package com.github.nlatyshev.xslt

import static com.github.nlatyshev.xslt.Entity.*

/**
 * @author nlatyshev 26/02/2016.
 */
class Transformations extends TransformationFactory {
    {
        transform COLLATERAL_DETAIL, {e ->
            each e.col_dtl, {dtl ->
                dtl.col_dtl_id = isAsset(dtl) ?
                        md5(dtl.col_agrmt_id|dtl.col_agrmt_src_nme|(dtl.cas_id?:dtl.ins_id)) :
                        md5(dtl.col_agrmt_id|dtl.prvr_pty_id|dtl.prvr_pty_src_nme|dtl.col_agrmt_src_nme)

            }
        }

        transform COLLATERAL_COVERAGE, {e ->
            each e.col_covg_lnkge, {lnkge ->
                switch (lnkge.covg_typ_indctr) {
                    case 'S':
                        lnkge.col_covg_lnkge_id = md5(lnkge.cont_src_nme|lnkge.cont_id); break
                    case 'P':
                        lnkge.col_covg_lnkge_id = md5(lnkge.cpty_pty_src_nme|lnkge.cpty_pty_id|lnkge.grc_pdt); break
                    case 'L':
                        lnkge.col_covg_lnkge_id = md5(lnkge.fac_lmt_src_nme|lnkge.fac_lmt_id); break
                    case 'F':
                        lnkge.col_covg_lnkge_id = md5(lnkge.fac_agrmt_src_nme|lnkge.fac_agrmt_id); break
                    case 'M':
                        lnkge.col_covg_lnkge_id = md5(lnkge.legl_agrmt_id); break
                    case 'C':
                        lnkge.col_covg_lnkge_id = md5(lnkge.cpty_pty_src_nme|lnkge.cpty_pty_id); break
                    case 'LC':
                        lnkge.col_covg_lnkge_id = md5(lnkge.cont_src_nme|lnkge.cont_id); break
                    case 'MC':
                        lnkge.col_covg_lnkge_id = md5(lnkge.legl_agrmt_id|lnkge.abs_clo_tx_id); break
                    default:
                        lnkge.col_covg_lnkge_id = md5('')
                }
            }
        }
    }
}
