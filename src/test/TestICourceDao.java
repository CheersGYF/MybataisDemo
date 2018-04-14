package test;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import com.neuedu.mybatisdemo.bean.Cource;
import com.neuedu.mybatisdemo.bean.Pager;
import com.neuedu.mybatisdemo.bean.Student;
import com.neuedu.mybatisdemo.dao.ICourceDao;
import com.neuedu.mybatisdemo.utils.DateUtil;

public class TestICourceDao {
	private Logger logger=Logger.getLogger(TestICourceDao.class);
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
	public void testFindAll(){
		
		try {
			ICourceDao courceDao = session.getMapper(ICourceDao.class);
			// 3.调用数据访问接口，进行数据操作
			List<Cource> list = courceDao.findAll();
			// 4.处理数据操作的结果
			for(Cource cource:list){
				logger.info(cource.getCourceId()+","+cource.getCourceName()
				+","+cource.getCourceScore()+","+cource.getStartDate());
				
				// 查询课程对应的选课的学生
				if (cource.getStudents()!=null && cource.getStudents().size()>0) {
					for(Student student : cource.getStudents()){
						logger.info(student.getId()+","+student.getAccount()
						+","+student.getPassword()+","+student.getTruename());
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//@Test
	public void testFindById(){
		try {
			ICourceDao courceDao = session.getMapper(ICourceDao.class);
			
			// 3.调用数据访问接口，进行数据操作
			//Cource cource = null;
			//方法一：通过mybatis session先据获取mybatis映射接口，再调用接口的方法进行查询
			Cource cource=null;
			cource = courceDao.findById("couce001");
			//方法二 ： 直接使用mybatis session直接调用接口和方法，并进行参数传递
			//cource = session.selectOne("com.neuedu.mybatisdemo.dao.ICourceDao.findById","couce001");
			//List<Cource> list =session.selectList("com.neuedu.mybatisdemo.dao.ICourceDao.findById","couce001");
			
			// 4.处理数据操作的结果
			if (cource!=null) {
				logger.info(cource.getCourceId()+","+cource.getCourceName()
				+","+cource.getCourceScore()+","+cource.getStartDate());
			}else{
				logger.info("不存在此用户");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//@Test
	public void testAddCource(){
		Date date=null;
		try {
			date= new SimpleDateFormat("yyyy-MM-dd").parse("2018-3-10");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			date=new Date();// 时间转换失败使用当前时间
		}
		
		try {
			
			Cource cource = new Cource("cource002","mybatis实战-v.",4,date);
			session.getMapper(ICourceDao.class).addCource(cource);
			session.commit();
			logger.info("增加课程成功！");
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("新增课程失败！",e);
		}
		
	}
	//@Test
	public void testUpdateCource(){
		try {
			Cource cource = new Cource("cource002","mybatis实战001",100,null);
			cource = session.getMapper(ICourceDao.class).findById("cource002");
			if (cource!=null) {
				session.getMapper(ICourceDao.class).updateCource(cource);
				session.commit();
				logger.info("修改课程成功！");
			}else{
				logger.info("此课程不存在！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("修改课程失败！",e);
		}
		
	}
	//@Test
	public void testDeleteCource(){
		try {
			
			Cource cource = session.getMapper(ICourceDao.class).findById("cource002");
			if (cource!=null) {
				session.getMapper(ICourceDao.class).deleteCource("cource002");
				session.commit();
				logger.info("删除课程成功！");
			}else{
				logger.info("此课程不存在！");
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("删除课程失败！",e);
		}
	}
	//@Test
	public void testSearchByDate(){
		Cource cource = new Cource();
		String dateString = "2018-3-6";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date startDate = sdf.parse(dateString);
			cource.setStartDate(startDate);
			
			ICourceDao courceDao = session.getMapper(ICourceDao.class);
			List<Cource> list = courceDao.searchByDate(cource);
			if(list!=null){
				for(Cource cource2:list){
					logger.info(cource2.getCourceName());
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	//@Test
	public void testSearchByExample(){
		ICourceDao courceDao =session.getMapper(ICourceDao.class);
		Cource cource =new Cource();
		// 在设置搜索条件时，编写匹配规则；则编写sql时直接获取参数即可
		// 如果设置条件时没有添加匹配规则
		/*cource.setCourceName("%mybatis%");*/
		cource.setCourceName("mybatis");
		cource.setStartDate(DateUtil.parseDate("2018-03-08", DateUtil.SHORT));
		
		// 不定条件搜索
		List<Cource> list = courceDao.searchByExample(cource);
		if(list!=null){
			for(Cource cource2:list){
				logger.info(cource2.getCourceName());
			}
		}
	}
	//@Test
	public void testSearchByPage(){
		ICourceDao courceDao =session.getMapper(ICourceDao.class);
		
		Cource cource  = new Cource();
		cource.setCourceName("实战");
		cource.setStartDate(DateUtil.parseDate("2018-3-9", DateUtil.SHORT));
		cource.setCourceScore(3);
		Pager pager = new Pager(1,5,cource);
		// 在设置搜索条件时，编写匹配规则；则编写sql时直接获取参数即可
		// 如果设置条件时没有添加匹配规则
		/*cource.setCourceName("%mybatis%");*/
		
		// 不定条件搜索
		List<Cource> list = courceDao.searchByPage(pager);
		if(list!=null){
			for(Cource cource2:list){
				logger.info(cource2.getCourceName());
			}
		}
	}
	//@Test
	public void testBatchAdd(){
		List<Cource> list = new ArrayList<>();
		list.add(new Cource("coursev0001","mybatis进阶1",5,new Date()));
		list.add(new Cource("coursev0002","mybatis进阶2",5,new Date()));
		list.add(new Cource("coursev0003","mybatis进阶3",5,new Date()));
		
		SqlSession session = null;
		//1.加载mybatis环境
			String resource = "config/mybatis-config.xml";
			try {
				InputStream inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				// 2.通过mybatis实例化数据访问接口
				// session 有多种类型，在进行批量操作时可以使用ExecutorType.BATCh声明当前session执行的是批量操作
				session=sqlSessionFactory.openSession(ExecutorType.BATCH);
				ICourceDao courceDao = session.getMapper(ICourceDao.class);
				for(Cource cource:list){
					courceDao.addCource(cource);
				}
				session.commit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.warn("发生了异常！",e);
				e.printStackTrace();
			}
	}// end test
	
	
	//@Test
	public void testBatchUpdate(){
		List<String> list = new ArrayList<>();
		list.add("coursev0001");
		list.add("coursev0002");
		list.add("coursev0003");
		
		SqlSession session = null;
		//1.加载mybatis环境
			String resource = "config/mybatis-config.xml";
			try {
				InputStream inputStream = Resources.getResourceAsStream(resource);
				SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
				// 2.通过mybatis实例化数据访问接口
				// session 有多种类型，在进行批量操作时可以使用ExecutorType.BATCh声明当前session执行的是批量操作
				session=sqlSessionFactory.openSession();
				ICourceDao courceDao = session.getMapper(ICourceDao.class);
				courceDao.beatuchUpdate(list);
				session.commit();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.warn("发生了异常！",e);
				e.printStackTrace();
			}catch (RuntimeException e) {
				// TODO: handle exception
				session.rollback();
			}
	}// end test
}
	
