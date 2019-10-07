package hhx.enums;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayMethodEnumTypeHandler extends BaseTypeHandler<PayMethodEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PayMethodEnum parameter, JdbcType jdbcType) throws SQLException {

    }

    @Override
    public PayMethodEnum getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public PayMethodEnum getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public PayMethodEnum getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
