/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.library1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class AddLibraryMember implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int memberid;
    private String membername;
    private String gender;
 private String classname;
 private String section;
 private Date date;
  private int classid;
 private int sectionid;

    /** Creates a new instance of ExamTitleBean */
    public AddLibraryMember() {
    }

   public int getmemberid() {
        return memberid;
    }

    public void setmemberid(int memberid1) {
        this.memberid = memberid1;
    }
    
    public int getclassid() {
        return classid;
    }

    public void setclassid(int classid1) {
        this.classid = classid1;
    }

    public int getsectionid() {
        return sectionid;
    }

    public void setsectionid(int sectionid1) {
        this.sectionid = sectionid1;
    }
    public String getmembername() {
        return membername;
    }

    public void setmembername(String membername) {
        this.membername = membername;
    }

    
         public String getclassname() {
        return classname;
    }

        public void setclassname(String classname1) {
        this.classname = classname1;
    }
    
         public String getsection() {
        return section;
    }

    public void setsection(String section1) {
        this.section = section1;
    }
         public String getgender() {
        return gender;
    }

    public void setgender(String gender1) {
        this.gender = gender1;
    }
        
    public Date getDate() {
        return date;
    }

    public void setDate(Date date1) {
        this.date = date1;
    }

    
    public List<AddLibraryMember> getAllClasses() {
    List<AddLibraryMember> list = new ArrayList<AddLibraryMember>();
    String query = "SELECT class_id, class_name FROM sch_class_name;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        AddLibraryMember cb = new AddLibraryMember();
        cb.setclassid(rs.getInt("class_id"));
        cb.setclassname(rs.getString("class_name"));
        list.add(cb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(AddLibraryMember.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

  public List<AddLibraryMember> getAllSections() {
    List<AddLibraryMember> list = new ArrayList<AddLibraryMember>();
    String query = "SELECT section_id, section_name FROM sch_section_name ;";
    PreparedStatement ps;
    try {
      ps = DBConnect.getConnection().prepareStatement(query);
      ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        AddLibraryMember sb = new AddLibraryMember();
        sb.setsectionid(rs.getInt("section_id"));
        sb.setsection(rs.getString("section_name"));
        list.add(sb);
      }
    } catch (SQLException ex) {
      Logger.getLogger(AddLibraryMember.class.getName()).log(Level.SEVERE, null, ex);
    }
    return list;
  }

    
    
    
    
  
    public void insertNewMember() {
            DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    String dob1 = df1.format(this.getDate());
  
        
        String sql1 = "INSERT INTO addmember (id,name,class,section,gender,date) VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql1);
            ps.setInt(1, this.getmemberid());
            ps.setString(2, this.getmembername());
            ps.setString(3, this.getclassname());
            ps.setString(4, this.getsection());
            ps.setString(5, this.getgender());
            ps.setString(6, dob1);
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to save data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>insert Error");
        }
        //System.out.println("insert successfully");
    }

    public List<AddLibraryMember> getAllMembers() {
        List<AddLibraryMember> data = new ArrayList<AddLibraryMember>();
        String sql = "SELECT  id, name,class ,section,gender,date  FROM addmember;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddLibraryMember et = new AddLibraryMember();
                et.setmemberid(rs.getInt("id"));
                et.setmembername(rs.getString("name"));
                et.setclassname(rs.getString("class"));
                et.setsection(rs.getString("section"));
                et.setgender(rs.getString("gender"));
                et.setDate(rs.getDate("date"));
                data.add(et);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>get Exam title Error");
        }
        return data;
    }
/*
    public void updateExam() {
        String sql = "UPDATE sch_exam_title SET exam_title=?, description=? WHERE ex_tt_id=?; ";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setString(1, this.getExamTitle());
            ps.setString(2, this.getExamDescription());
            ps.setInt(3, this.getExamTitleId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>updateExam Error");
        }
    }

    public void deleteExam() {
        String sql = "DELETE FROM sch_exam_title WHERE ex_tt_id=?;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getExamTitleId());
            int i = ps.executeUpdate();
            if (i > 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "One row deleted");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to delete data");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "------>deleteExam Error");
        }
    }
     * 
     */
}
