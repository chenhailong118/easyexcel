package com.alibaba.excel.analysis.v03.handlers;

import java.math.BigDecimal;

import org.apache.poi.hssf.record.NumberRecord;
import org.apache.poi.hssf.record.Record;

import com.alibaba.excel.analysis.v03.IgnorableXlsRecordHandler;
import com.alibaba.excel.constant.BuiltinFormats;
import com.alibaba.excel.context.XlsReadContext;
import com.alibaba.excel.enums.RowTypeEnum;
import com.alibaba.excel.metadata.CellData;

/**
 * Record handler
 *
 * @author Dan Zheng
 */
public class NumberRecordHandler implements IgnorableXlsRecordHandler {

    @Override
    public void processRecord(XlsReadContext xlsReadContext, Record record) {
        NumberRecord nr = (NumberRecord)record;
        CellData cellData = CellData.newInstance(BigDecimal.valueOf(nr.getValue()), nr.getRow(), (int)nr.getColumn());
        Integer dataFormat = xlsReadContext.formatTrackingHSSFListener().getFormatIndex(nr);
        cellData.setDataFormat(dataFormat);
        cellData.setDataFormatString(
            BuiltinFormats.getBuiltinFormat(dataFormat, xlsReadContext.formatTrackingHSSFListener().getFormatString(nr),
                xlsReadContext.readSheetHolder().getGlobalConfiguration().getLocale()));
        xlsReadContext.cellMap().put((int)nr.getColumn(), cellData);
        xlsReadContext.tempRowType(RowTypeEnum.DATA);
    }
}
