package test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.neuedu.mybatisdemo.bean.Cource;
import com.neuedu.mybatisdemo.bean.Student;
import com.neuedu.mybatisdemo.dao.IStudentDao;

public class TestIStudentDao {
	private Logger logger=Logger.getLogger(TestIStudentDao.class);
	private SqlSession session = null;
	@Before 
	public void init(){
		//1.加载mybatis环境
		String resource = "config/mybatis-config.xml";
		try {
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			// 2.通过mybatis实例化数据访问接口
			session=sqlSessionFactory.openSession();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.warn("发生了异常！",e);
			e.printStackTrace();
		}
	}
	@After
	public void destory(){
		if(session !=null){
			session.close();
		}
	}
	//@Test
	public void testAddStudent(){
		Student student =new Student("lisi","123456","李四");
		IStudentDao studentDao =session.getMapper(IStudentDao.class);
		//此处返回的是statement.executeUpdate(sql)值，默认值为0，返回1表示有一条记录执行成功
		int count =studentDao.addStudent(student);
		session.commit();
		if(count==1){
			logger.info("学生信息新增成功！");
		}else{
			logger.info("学生信息新增失败！");
		}
		
	}
	//@Test
	public void testEditStudent(){
		Student student =new Student(6,"li5","3968369","李四");
		IStudentDao studentDao =session.getMapper(IStudentDao.class);
		//此处返回的是statement.executeUpdate(sql)值，默认值为0，返回1表示有一条记录执行成功
		int count =studentDao.editStudent(student);
		session.commit();
		if(count==1){
			logger.info("学生信息修改成功！");
		}else{
			logger.info("学生信息修改失败！");
		}
		
	}
	//@Test
	public void testDeleteStudent(){
		IStudentDao studentDao =session.getMapper(IStudentDao.class);
		//此处返回的是statement.executeUpdate(sql)值，默认值为0，返回1表示有一条记录执行成功
		int count =studentDao.deleteStudent(8);
		session.commit();
		if(count==1){
			logger.info("学生信息删除成功！");
		}else{
			logger.info("学生信息删除失败！");
		}
		
	}
	//@Test
	public void testFindAll(){
		IStudentDao studentDao =session.getMapper(IStudentDao.class);
		List<Student> list = studentDao.findAll();
		for(Student student:list){
			logger.info(student.getId()+","+student.getAccount()
			+","+student.getPassword()+","+student.getTruename());
			
			if(student.getCources()!=null&&student.getCources().size()>0){
				for(Cource cource : student.getCources()){
					logger.info(cource.getCourceId()+","+cource.getCourceName()+","+
							cource.getCourceScore()+","+cource.getStartDate()
							);
				}
			}
		}
	}
	//@Test
	public void testFindAllInfo(){
		IStudentDao studentDao =session.getMapper(IStudentDao.class);
		List<Student> list = studentDao.findAllInfo();
		for(Student student:list){
			logger.info(student.getId()+","+student.getAccount()
			+","+student.getPassword()+","+student.getTruename());
			
			if(student.getContactInfo()!=null){
				logger.info(student.getContactInfo().getStudentId()+","+student.getContactInfo().getQq()+","+
						student.getContactInfo().getEmail());
			}
			
			
		}
	}
	@Test
	public void testFindById(){
		IStudentDao studentDao =session.getMapper(IStudentDao.class);
		Student student= studentDao.findById(2);
		if (student!=null) {
			// 查询出学生表信息
			logger.info(student.getId()+","+student.getAccount()
			+","+student.getPassword()+","+student.getTruename());
			//差询出学生练习方式信息
			if(student.getContactInfo()!=null){
				logger.info(student.getContactInfo().getStudentId()+","+student.getContactInfo().getQq()+","+
						student.getContactInfo().getEmail());
			}
			//查询学生的课程信息
			if(student.getCources()!=null&&student.getCources().size()>0){
				for(Cource cource : student.getCources()){
					logger.info(cource.getCourceId()+","+cource.getCourceName()+","+
							cource.getCourceScore()+","+cource.getStartDate()
							);
				}
			}
			
		}
	}
}
	
