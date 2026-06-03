package com.vti.backend.repository.impl;

import com.vti.backend.repository.ICandidateRepository;
import com.vti.entity.Candidate;
import com.vti.entity.ExperienceCandidate;
import com.vti.entity.FresherCandidate;
import com.vti.enums.GraduationRank;
import com.vti.utils.JDBCUtils;

import java.sql.*;

public class JdbcCandidateRepositoryImpl implements ICandidateRepository {

    @Override
    public boolean save(Candidate candidate) {
        if (candidate == null) return false;
        Connection conn = JDBCUtils.getConnection();
        if (conn == null) return false;
        String sql = "INSERT INTO Candidate (FirstName, LastName, Phone, Email, Password, Role, ExpInYear, ProSkill, GraduationRank) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, candidate.getFirstName());
            ps.setString(2, candidate.getLastName());
            ps.setString(3, candidate.getPhone());
            ps.setString(4, candidate.getEmail());
            ps.setString(5, candidate.getPassword());
            String role = null;
            if (candidate.getRole() != null) {
                switch (candidate.getRole()) {
                    case EXPERIENCE: role = "Experience"; break;
                    case FRESHER: role = "Fresher"; break;
                    default: role = candidate.getRole().name();
                }
            }
            ps.setString(6, role);

            if (candidate instanceof ExperienceCandidate) {
                ExperienceCandidate e = (ExperienceCandidate) candidate;
                ps.setObject(7, e.getExpInYear(), Types.INTEGER);
                ps.setString(8, e.getProSkill());
                ps.setString(9, null);
            } else if (candidate instanceof FresherCandidate) {
                FresherCandidate f = (FresherCandidate) candidate;
                ps.setObject(7, null, Types.INTEGER);
                ps.setString(8, null);
                if (f.getGraduationRank() != null) ps.setString(9, f.getGraduationRank().name()); else ps.setString(9, null);
            } else {
                ps.setObject(7, null, Types.INTEGER);
                ps.setString(8, null);
                ps.setString(9, null);
            }

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            JDBCUtils.closeConnection(conn, ps, null);
        }
    }

    @Override
    public Candidate findByEmailAndPassword(String email, String password) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null) return null;
        String sql = "SELECT * FROM Candidate WHERE Email = ? AND Password = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("Role");
                String first = rs.getString("FirstName");
                String last = rs.getString("LastName");
                String phone = rs.getString("Phone");
                String mail = rs.getString("Email");
                String pass = rs.getString("Password");

                if ("Experience".equalsIgnoreCase(role)) {
                    ExperienceCandidate e = new ExperienceCandidate();
                    e.setFirstName(first); e.setLastName(last); e.setPhone(phone); e.setEmail(mail); e.setPassword(pass);
                    int exp = rs.getInt("ExpInYear");
                    if (rs.wasNull()) e.setExpInYear(-1); else e.setExpInYear(exp);
                    e.setProSkill(rs.getString("ProSkill"));
                    return e;
                } else if ("Fresher".equalsIgnoreCase(role)) {
                    FresherCandidate f = new FresherCandidate();
                    f.setFirstName(first); f.setLastName(last); f.setPhone(phone); f.setEmail(mail); f.setPassword(pass);
                    String rankStr = rs.getString("GraduationRank");
                    try {
                        if (rankStr != null) f.setGraduationRank(GraduationRank.valueOf(rankStr));
                    } catch (IllegalArgumentException iae) {
                        // ignore unknown value
                    }
                    return f;
                } else {
                    Candidate c = new Candidate();
                    c.setFirstName(first); c.setLastName(last); c.setPhone(phone); c.setEmail(mail); c.setPassword(pass);
                    return c;
                }
            }
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            JDBCUtils.closeConnection(conn, ps, rs);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        Connection conn = JDBCUtils.getConnection();
        if (conn == null) return false;
        String sql = "SELECT 1 FROM Candidate WHERE Email = ? LIMIT 1";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            JDBCUtils.closeConnection(conn, ps, rs);
        }
    }
}
