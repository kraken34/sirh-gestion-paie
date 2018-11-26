package dev.paie.util;

import java.sql.ResultSet;

import org.springframework.jdbc.core.RowMapper;

import dev.paie.entite.Grade;

public class GradeMapper {
    RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
        Grade g = new Grade();
        g.setId(rs.getInt("id"));
        g.setCode(rs.getString("code"));
        g.setNbHeuresBase(rs.getBigDecimal("nbheuresbase"));
        g.setTauxBase(rs.getBigDecimal("tauxhoraire"));
        return g;
    };
}
