package bitcamp.java89.ems.server.vo;

import java.io.Serializable;

public class Teacher implements Serializable{ 
  private static final long serialVersionUID = 1L;
// 새로운 클래스 설정
   // 인스턴스 변수
   protected String name;
   protected String gender;
   protected int age;
   protected String career;
   protected String skill;
   protected String major;
   protected String email;
   protected String tel;


public Teacher() {}

public Teacher(String name, int age, String skill, String career) {
  this.name = name;
  this.age = age;
  this.skill = skill;
  this.career = career;
}

public String getName() {
  return name;
}

public void setName(String name) {
  this.name = name;
}

public String getGender() {
  return gender;
}

public void setGender(String gender) {
  this.gender = gender;
}

public int getAge() {
  return age;
}

public void setAge(int age) {
  this.age = age;
}

public String getCareer() {
  return career;
}

public void setCareer(String career) {
  this.career = career;
}

public String getSkill() {
  return skill;
}

public void setSkill(String skill) {
  this.skill = skill;
}

public String getMajor() {
  return major;
}

public void setMajor(String major) {
  this.major = major;
}

public String getEmail() {
  return email;
}

public void setEmail(String email) {
  this.email = email;
}

public String getTel() {
  return tel;
}

public void setTel(String tel) {
  this.tel = tel;
}

@Override
public String toString() {
  return "Teacher [name=" + name + ", gender=" + gender + ", age=" + age + ", career=" + career + ", skill=" + skill
      + ", major=" + major + ", email=" + email + ", tel=" + tel + "]";
  }
}
