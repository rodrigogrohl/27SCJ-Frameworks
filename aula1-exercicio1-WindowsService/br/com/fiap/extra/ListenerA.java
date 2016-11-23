package br.com.fiap.extra;

import java.io.File;
import java.util.Date;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaxen.BaseXPath;
import org.jaxen.dom.DOMXPath;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerUtils;
import org.quartz.impl.StdSchedulerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ListenerA  implements Job{

	private static final Logger LOG = LogManager.getLogger(ListenerA.class);
	private static PropertiesConfiguration configProperties = null;
	private int executeIntervalInSeconds;
	private String path = null;
	private String destinationPath = null;
	private static ListenerA server;
	
	public ListenerA() {
		try {
			configProperties = new PropertiesConfiguration("properties/config.properties");
			executeIntervalInSeconds = configProperties.getInt("executeIntervalInSeconds");
			path = configProperties.getString("filesPath");
			destinationPath = configProperties.getString("destinationPath");
		} catch (Exception e) {
			LOG.error("Ocorreu ao carregar parametros do arquivo de propriedades [config.properties].", e);
		}
	}
	
	public static void main(String[] args) throws Exception {
		server = new ListenerA();
		
		//TODO: teste local
		//server.parser();
		
		try {
			server.startScheduler();
		} catch (SchedulerException ex) {
			LOG.error("Falha ao iniciar scheduler Quartz.", ex);
		}
	}
	
	/**
	 * Configura e inicia o scheduler
	 * @throws SchedulerException - Excecao do Quartz Framework
	 */
	protected void startScheduler()
		throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
		JobDetail jobDetail =
			new JobDetail(
				"fileParser",
				"DEFAULT",
				br.com.fiap.extra.ListenerA.class);
		jobDetail.getJobDataMap().put("fileParser", "fileParser");

		//intervalo de execucao
		Trigger trigger = TriggerUtils.makeSecondlyTrigger(executeIntervalInSeconds);
		trigger.setName("fileParserTrigger");
		trigger.setStartTime(new Date());
		trigger.setJobName("fileParser");
		trigger.setVolatility(false);
		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.start();
	}

	/**
	 * Metodo invocado pelo scheduler Quartz 
	 * 
	 * @throws JobExecutionException - Excecao do Quartz Framework
	 */
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		try {
			LOG.info("Parser dos arquivos iniciado.");
			server.parser();
			LOG.info("Parser dos arquivos finalizado.");
		} catch (Exception e) {
			LOG.error("Ocorreu erro no processo de parser dos arquivos.", e);
		}
	}
	
	/**
	 * Parser dos arquivos.
	 * 
	 * @throws Exception - Excecao ...
	 */
	private void parser() throws Exception {
		
		Element element = null;
		BaseXPath base = null;
		
		@SuppressWarnings("unchecked")
		Iterator iterator = FileUtils.iterateFiles(new File(path), new String[]{"28"}, false);
		while (iterator.hasNext()) {
			File file = (File) iterator.next();
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(file);
			element = document.getDocumentElement();
			base = new DOMXPath("layout/id");
			LOG.info("Movendo arquivo: " + file.getAbsolutePath());
			
			if (!new File(destinationPath).exists()){
				new File(destinationPath).mkdirs();
			}
			FileUtils.moveFile(file, new File(destinationPath + File.separator + base.stringValueOf(element) + ".28"));
		}
	}


}
