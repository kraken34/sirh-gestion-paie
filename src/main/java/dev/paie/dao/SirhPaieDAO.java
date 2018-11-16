package dev.paie.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import dev.paie.entite.Grade;

@Repository
public class SirhPaieDAO {
	// outil JdbcTemplate fourni par Spring JDBC
	private JdbcTemplate jdbcTemplate;

	@Autowired // injection de la source de données
	public SirhPaieDAO(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	public void ajoutGrade(Grade grade) {

		// insertion
		String sql = "INSERT INTO GRADE (CODE, NBHEUREBASE, TAUXBASE) VALUES(?,?,?)";
		jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase());

	}

	public void updateGrade(Grade grade) {
		// mise à jour de données
		String sqlUpdate = "UPDATE GRADE SET code=?, nbheurebase=?, tauxbase=? WHERE ID = ? ";
		jdbcTemplate.update(sqlUpdate, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase(), grade.getId());
	}
	
	public Grade getGradeByCode(String code) {
		String sql = "SELECT * FROM GRADE WHERE CODE = ?";
		return jdbcTemplate.queryForObject(sql, new GradeMapper(), code);
	}
	
	public Grade getGradeById(Integer id) {
		String sql = "SELECT * FROM GRADE WHERE ID = ?";
		return jdbcTemplate.queryForObject(sql, new GradeMapper(), id);
	}
}
