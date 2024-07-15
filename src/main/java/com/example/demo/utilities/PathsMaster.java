package com.example.demo.utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.demo.Dao.BanksConfigurationRepository;


@Component
public class PathsMaster {
	
	
	@Value("${app.FromNPCIMDT}")
	String FromNPCIMDT;
	
	@Value("${app.MDTRTN}")
	String MDTRTN;
	
	@Value("${app.InputMDT}")
	String InputMDT;
	
	@Value("${app.INPUTMDT_ARCH}")
	String INPUTMDT_ARCH;
	
	@Value("${app.FromNPCIMDTArchive}")
	String FromNPCIMDTArchive;
	
	@Value("${app.InpSigned}")
	String InpSigned;
	
	@Value("${app.InpToCbs}")
	String InpToCbs;
	
	@Value("${app.InpToCbsArch}")
	String InpToCbsArch;
	
	@Value("${app.INPUT}")
	String INPUT;
	
	@Value("${app.INPUT_FAIL}")
	String INPUT_FAIL;
	
	@Value("${app.INPUT_ARCH}")
	String INPUT_ARCH;
	
	@Value("${app.output_fail}")
	String output_fail;
	
	@Value("${app.INP}")
	String INP;
	
	@Value("${app.InpFromOmc}")
	String InpFromOmc;
	
	@Value("${app.InpFromOmcArch}")
	String InpFromOmcArch;
	
	@Value("${app.ResToOmc}")
	String ResToOmc;
	
	@Value("${app.ResToOmcArch}")
	String ResToOmcArch;
	
	@Value("${app.ResSigned}")
	String ResSigned;
	
	@Value("${app.InpToOmc}")
	String InpToOmc;
	
	@Value("${app.ResFromOmc}")
	String ResFromOmc;
	
	@Value("${app.MANDT_INP_FILES}")
	String MANDT_INP_FILES;
	
	@Value("${app.INPToNPCI}")
	String INPToNPCI;
	
	@Value("${app.INP_To_NPCI_ARCH}")
	String INP_To_NPCI_ARCH;
		
	@Value("${app.INP_MDT_SIGNED_TO_NPCI}")
	String INP_MDT_SIGNED_TO_NPCI;
	


	
    BanksConfigurationRepository configRepo;
	
     @Autowired
	 public PathsMaster(BanksConfigurationRepository configRepo) 
	 {
	        this.configRepo = configRepo;
	 }
    

	public PathsMaster() {
		super();
		// TODO Auto-generated constructor stub
	}


	private String getRootpath(String bankname) {
		
		String rootpath = configRepo.getRootpath(bankname);
		
		return rootpath;
		
	}
	
	public String getMDTInwFromNpci(String bankName, String productType)
	{
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = FromNPCIMDT.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	     e.printStackTrace();
	    } 
	    return path;
	 }
	
	public String getMDTReturnToNpci(String bankName, String productType) 
	{
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = MDTRTN.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } catch (Exception e) 
	    {
	    	e.printStackTrace();
	    } 
	    return path;
	}
	  
	  public String getMDTInwardSinged(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = InputMDT.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getMDTInwardSingedArchive(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = INPUTMDT_ARCH.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInwMDTFromNpciArchive(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = FromNPCIMDTArchive.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getAccountValidationSignedInputPath(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = InpSigned.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getAccountValidationInputToCbsPath(String bankName, String temp) 
	  {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getAccountValidationInputToCbsArchPath(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = InpToCbsArch.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  

	  public String getInwardSinged(String bankName, String productType) 
	  {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = INPUT.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInwardSignedfailed(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = INPUT_FAIL.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInwardSingedArchive(String bankName, String productType) 
	  {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = INPUT_ARCH.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getReturnedSignedFail(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = output_fail.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  
	  public String getInp(String bankName, String productType) 
	  {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = INP.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }

	  public String getInputFromOmc(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = InpFromOmc.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInputFromOmcArchive(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = InpFromOmcArch.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	 
	 
	  public String getResToOmc(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = ResToOmc.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getResToOmcArch(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = ResToOmcArch.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getResToOmcSigned(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = ResSigned.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	 
	  public String getInpToOmc(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp =InpToOmc.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getResponseFromOmc(String bankName, String productType) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      String temp = ResFromOmc.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  
	  public String getDownloadedMndtInpFiles(String bankName, String productType) {
	    String path = "";
	    try {
	      String root = getRootpath(bankName);
	      String temp = MANDT_INP_FILES.trim();
	      path = String.valueOf(root) + bankName + temp;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInpToNPCI(String bankName, String dirname) 
	  {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      
	      path = String.valueOf(root) + bankName + dirname;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInpToNPCITEMP(String bankName, String dirname) 
	  {
		    String path = "";
		    try 
		    {
		      String root = getRootpath(bankName);
		      path = String.valueOf(root) + bankName + dirname;
		    } 
		    catch (Exception e) 
		    {
		      e.printStackTrace();
		    } 
		    return path;
	  }
	  
	  public String getTransInpFileToNPCIArch(String bankName, String productType) 
	  {
		    String path = "";
		    try
		    {
		      String root = getRootpath(bankName);
		      String temp = INP_To_NPCI_ARCH.trim();

		      path = String.valueOf(root) + bankName + temp;
		    } 
		    
		   catch (Exception e) 
		    {
		      e.printStackTrace();
		    } 
		    return path;
	}
	  
	  public  String getMndtInpFiles(String bankName,String inpfilename) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      System.out.println("Root val -->"+root);
	      System.out.println("tempval -->"+inpfilename);
	      path = String.valueOf(root) + bankName + inpfilename;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }
	  
	  public String getInpMdtSignedToNPCI(String bankName, String filename) {
	    String path = "";
	    try 
	    {
	      String root = getRootpath(bankName);
	      path = String.valueOf(root) + bankName + filename;
	    } 
	    catch (Exception e) 
	    {
	      e.printStackTrace();
	    } 
	    return path;
	  }

}