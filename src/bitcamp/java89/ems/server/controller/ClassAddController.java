// 강의실

package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassDao;
import bitcamp.java89.ems.server.vo.ClassRoom;;

public class ClassAddController implements Command {
  private ClassDao classDao;

  public ClassAddController() throws Exception {
    classDao = ClassDao.getInstance();
  }

  // 강의실 데이터추가
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      if (classDao.existCname(paramMap.get("cName"))) {
        out.println("동일 강의실명 존재. 저장할 수 없습니다.");
        return;
      }

      ClassRoom classRoom = new ClassRoom(); // 임시 저장할 객체

      classRoom.setClassName(paramMap.get("cName"));
      classRoom.setFloorInfo(paramMap.get("fInfo"));
      classRoom.setCapacity(Integer.parseInt(paramMap.get("cIty")));
      classRoom.setCabinetQty(Integer.parseInt(paramMap.get("cQty")));
      classRoom.setAirconditionerQty(Integer.parseInt(paramMap.get("air")));
      classRoom.setProjectBim(paramMap.get("pBim").equals("y") ? true : false);

      classDao.insert(classRoom);
      out.println("등록 하였습니다.");
    } catch (Exception e) {
      out.println("데이터 저장 작업 중 에러 발생!");
    }
  }
}
