// 강의실

package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassDao;;

public class ClassDeleteController implements Command {
  private ClassDao classDao;

  public ClassDeleteController() throws Exception {
    classDao = ClassDao.getInstance();
  }

  // 강의실 데이터 삭제
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      if (!classDao.existCname(paramMap.get("cName"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      classDao.delete(paramMap.get("cName"));
      out.println("해당 데이터를 삭제하였습니다.");
    } catch (Exception e) {
      out.println("데이터 삭제 작업 중 에러 발생!");
    }
  }
}
