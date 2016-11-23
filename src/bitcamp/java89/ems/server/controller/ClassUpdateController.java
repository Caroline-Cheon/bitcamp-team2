// 강의실

package bitcamp.java89.ems.server.controller;
import java.io.PrintStream;
import java.util.HashMap;
import bitcamp.java89.ems.server.Command;
import bitcamp.java89.ems.server.dao.ClassDao;
import bitcamp.java89.ems.server.vo.ClassRoom;;

public class ClassUpdateController implements Command {
  private ClassDao classDao;

  public ClassUpdateController() throws Exception {
    classDao = ClassDao.getInstance();
  }

  // 강의실 데이터 변경
  public void service(HashMap<String, String> paramMap, PrintStream out) {
    try {
      if (!classDao.existCname(paramMap.get("cName"))) {
        out.println("해당 데이터가 없습니다.");
        return;
      }

      ClassRoom classRoom = new ClassRoom();
      classRoom.setClassName(paramMap.get("cName"));
      classRoom.setFloorInfo(paramMap.get("fInfo"));
      classRoom.setCapacity(Integer.parseInt(paramMap.get("cIty")));
      classRoom.setCabinetQty(Integer.parseInt(paramMap.get("cQty")));
      classRoom.setAirconditionerQty(Integer.parseInt(paramMap.get("air")));
      classRoom.setProjectBim(paramMap.get("pBim").equals("y") ? true : false);
      classDao.update(classRoom);
      
      out.println("해당 데이터를 갱신 하였습니다.");
    } catch (Exception e) {
      out.println("데이터 갱신 작업 중 에러 발생!");
    }
  }
}
