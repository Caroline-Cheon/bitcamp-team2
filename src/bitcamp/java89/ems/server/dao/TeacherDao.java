/* 작업내용
- LinkedList를 ArrayList로 교체한다.
- 11월 14일 저장기능 추가하기
changed 변수 추가
isChanged() 추가
save() 추가
11월 15일
직렬화 적용

 */
package bitcamp.java89.ems.server.dao;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import bitcamp.java89.ems.server.vo.Teacher;

public class TeacherDao {
  static TeacherDao obj;
  private String filename = "teacher-v1.7.data"; // 파일 네임을
  private ArrayList<Teacher> list; // 리스트 만들고 초기화 아래서 함.

  //private Scanner keyScan;
  
  public static TeacherDao getInstance() {
    if (obj == null) {
      obj = new TeacherDao();
    }
    return obj;
  }

  private TeacherDao() { 
    this.load(); // 기존의 데이터 파일을 읽어서 ArrayList에 학생 정보를 로딩한다.
  }

 public ArrayList<Teacher> getList() {
   return this.list;
 }
 synchronized public Teacher getOne(String name) {
   for (Teacher teacher : list) {
     if (teacher.getName().equals(name)) {
       return teacher;
     }
   }
   return null;
 }
 
 
 public void insert(Teacher teacher) {
    list.add(teacher);
    try{this.save();} catch (Exception e) {}
 }
 
synchronized public void update(Teacher teacher) {
   for (int i = 0; i < list.size(); i++) {
     if (list.get(i).getName().equals(teacher.getName())) {
      // 리스트에서 i번째 항목을 꺼내(컨택객체)에 대해서 이메일 꺼내고. 파라미터로 넘어온 getEmail과 비교해 맞다면 교체하라.
       list.set(i, teacher);
       try{this.save();} catch (Exception e) {}
        return;
      }
    }
 }
 
 synchronized public void delete(String name) {
   for (int i = 0; i < list.size(); i++) {
     if (list.get(i).getName().equals(name)) {
       list.remove(i);
       try{this.save();} catch (Exception e) {}
       return;
     } 
   }
 }


 public boolean existName(String name) {
   for (Teacher teacher : list) {
     if (teacher.getName().toLowerCase().equals(name.toLowerCase())) {
       return true;
     }
   }
   return false;
 }
 
 @SuppressWarnings("unchecked")
 private void load() {
   FileInputStream in0 = null;
   ObjectInputStream in = null;
   
   try {
     in0 = new FileInputStream(this.filename);
     in = new ObjectInputStream(in0);

     list = (ArrayList<Teacher>)in.readObject();
     
   } catch (EOFException e) {
     // 파일을 모두 읽었다.
   } catch (Exception e) {
     System.out.println("학생 데이터 로딩 중 오류 발생!");
     list = new ArrayList<>();
   } finally {
     try {
       in.close();
       in0.close();
     } catch (Exception e) {
       // close하다가 예외 발생하면 무시한다.
     }
   }
 }

 synchronized public void save() throws Exception {
   FileOutputStream out0 = new FileOutputStream(this.filename);
   ObjectOutputStream out = new ObjectOutputStream(out0);

   out.writeObject(list);
   
   out.close();
   out0.close();
 }
}
