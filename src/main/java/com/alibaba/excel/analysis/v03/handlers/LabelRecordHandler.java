package com.alibaba.excel.analysis.v03.handlers;

import org.apache.poi.hssf.record.LabelRecord;
import org.apache.poi.hssf.record.Record;

import com.alibaba.excel.analysis.v03.IgnorableXlsRecordHandler;
import com.alibaba.excel.context.XlsReadContext;
import com.alibaba.excel.enums.RowTypeEnum;
import com.alibaba.excel.metadata.CellData;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class LabelRecordHandler implements IgnorableXlsRecordHandler {
    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        LabelRecord lrec = (LabelRecord)record;
        xlsReadContext.cellMap().put((int)lrec.getColumn(),
            CellData.newInstance(lrec.getValue(), lrec.getRow(), (int)lrec.getColumn()));
        xlsReadContext.tempRowType(RowTypeEnum.DATA);
    }
}
