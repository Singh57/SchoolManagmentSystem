/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package school.library1;

import com.mysql.jdbc.PreparedStatement;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import school.academic.SectionBean;
import school.util.DBConnect;

/**
 *
 * @author Apple
 */
public class MemberUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private int bookid;
    private String bookname;
    private String author;
 private String noofbooks;
 private String rackno;
 private String subject;
 private AddLibraryBooksBean selectedRow;

    
    

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

     public AddLibraryBooksBean getSelectedRow() {
    return selectedRow;
  }

  public void setSelectedRow(AddLibraryBooksBean selectedRow1) {
    this.selectedRow = selectedRow1;
  }
    
    public void doSetSelectedRow() {
  setbookid(getSelectedRow().getbookid());
    setbookname(getSelectedRow().getbookname());
    setauthor(getSelectedRow().getauthor());
    setnoofbooks(getSelectedRow().getnoofbooks());
    setrackno(getSelectedRow().getrackno());
    setsubject(getSelectedRow().getsubject());
     System.out.println("OK1");
    }
    
    public void prepareUpdate(){
    setbookid(getSelectedRow().getbookid());
    setbookname(getSelectedRow().getbookname());
    setauthor(getSelectedRow().getauthor());
    setnoofbooks(getSelectedRow().getnoofbooks());
    setrackno(getSelectedRow().getrackno());
    setsubject(getSelectedRow().getsubject());
    System.out.println("OK11");
  }
    
  public void updateBooks() {
    String sql = "UPDATE addbooks SET bookname=?,author=?,noofbooks=?,rackno=?,subject=? where bookid=? ";
    try {
      PreparedStatement ps = (PreparedStatement) DBConnect.getConnection().prepareCall(sql);
            ps.setString(1, this.getbookname());
            ps.setString(2, this.getauthor());
            ps.setString(3, this.getnoofbooks());
            ps.setString(4, this.getrackno());
            ps.setString(5, this.getsubject());
            ps.setInt(6, this.getbookid());
         int i = ps.executeUpdate();
      if (i > 0) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Information", "Data Update Successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      } 
      else {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Fail to update data");
        FacesContext.getCurrentInstance().addMessage(null, msg);
      }
   //   clear();
    } catch (Exception e) {
    }
  }

  void clear() {
  
  }
}
