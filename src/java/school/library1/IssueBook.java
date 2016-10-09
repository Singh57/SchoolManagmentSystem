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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class IssueBook implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int bookid;
    private String bookname;
    private String author;
 private String noofbooks;
 private String rackno;
 private String subject;
 private Date date1;
 private Date date2;

    /** Creates a new instance of ExamTitleBean */
    public IssueBook() {
    }

   public int getbookid() {
        return bookid;
    }

    public void setbookid(int bookid1) {
        this.bookid = bookid1;
    }

    public String getbookname() {
        return bookname;
    }

    public void setbookname(String bookname1) {
        this.bookname = bookname1;
    }

         public String getauthor() {
        return author;
    }

    public void setauthor(String author1) {
        this.author = author1;
    }
    
         public String getnoofbooks() {
        return noofbooks;
    }

    public void setnoofbooks(String noofbooks1) {
        this.noofbooks = noofbooks1;
    }
         public String getrackno() {
        return rackno;
    }

    public void setrackno(String rackno1) {
        this.rackno = rackno1;
    }
         public String getsubject() {
        return subject;
    }

    public void setsubject(String subject1) {
        this.subject = subject1;
    }

  public Date getDate1() {
    return date1;
  }

  public void setDate1(Date date11) {
    this.date1 = date11;
  }
   
  public Date getDate2() {
    return date2;
  }

  public void setDate2(Date date22) {
    this.date2 = date22;
  }
  public void insertBooks() {
      
      DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
    String dob1 = df1.format(this.getDate1());
    
    DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
    String dob2 = df2.format(this.getDate2());
        String sql1 = "INSERT INTO issuebook (bookid,bookname,author,noofbooks,date1,date2) VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql1);
            ps.setInt(1, this.getbookid());
            ps.setString(2, this.getbookname());
            ps.setString(3, this.getauthor());
            ps.setString(4, this.getnoofbooks());
            ps.setString(5, dob1);
            ps.setString(6, dob2);
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

    public List<IssueBook> getAllBooks() {
        
       

        List<IssueBook> data = new ArrayList<IssueBook>();
        String sql = "SELECT  bookid, bookname, author, noofbooks, date1, date2  FROM issuebook;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                IssueBook et = new IssueBook();
                et.setbookid(rs.getInt("bookid"));
                et.setbookname(rs.getString("bookname"));
                et.setauthor(rs.getString("author"));
                et.setnoofbooks(rs.getString("noofbooks"));
                et.setDate1(rs.getDate("date1"));
                et.setDate2(rs.getDate("date2"));
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
