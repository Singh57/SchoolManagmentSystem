/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.library1;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.util.DBConnect;

/**
 *
 * @author MASHUK
 */
public class AddLibraryBooksBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    private int bookid;
    private String bookname;
    private String author;
 private String noofbooks;
 private String rackno;
 private String subject;

    /** Creates a new instance of ExamTitleBean */
    public AddLibraryBooksBean() {
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

  
    public void insertBooks() {
        String sql1 = "INSERT INTO addbooks (bookid,bookname,author,noofbooks,rackno,subject) VALUES(?,?,?,?,?,?);";

        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql1);
            ps.setInt(1, this.getbookid());
            ps.setString(2, this.getbookname());
            ps.setString(3, this.getauthor());
            ps.setString(4, this.getnoofbooks());
            ps.setString(5, this.getrackno());
            ps.setString(6, this.getsubject());
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

    public List<AddLibraryBooksBean> getAllBooks() {
        List<AddLibraryBooksBean> data = new ArrayList<AddLibraryBooksBean>();
        String sql = "SELECT  bookid, bookname, author, noofbooks, rackno, subject  FROM addbooks;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AddLibraryBooksBean et = new AddLibraryBooksBean();
                et.setbookid(rs.getInt("bookid"));
                et.setbookname(rs.getString("bookname"));
                et.setauthor(rs.getString("author"));
                et.setnoofbooks(rs.getString("noofbooks"));
                et.setrackno(rs.getString("rackno"));
                et.setsubject(rs.getString("subject"));
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
*/
    public void deleteBook() {
        String sql = "DELETE FROM addbooks WHERE bookid=?;";
        try {
            PreparedStatement ps = DBConnect.getConnection().prepareStatement(sql);
            ps.setInt(1, this.getbookid());
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
     
}
