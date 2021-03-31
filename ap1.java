/*
 * Name   : Tanya Gupta
 * Roll No: 2019119
 * Branch : CSE
 * Section: A
 * Assignment 1
 */
import java.util.*;
class Patient
{   
	private String name;
	private int age;
	private float bodyTemp;
	private int oxyLevel;
	private int pId;
	private String status="Not Admitted";
	private institute insti=null;
	private static int id=0;
	private int recTime;
	
	public String getname() { return this.name;}
	public int getage() { return this.age;}
	public float getbodyTemp() { return this.bodyTemp;}
	public int getoxyLevel() {return this.oxyLevel;}
	public int getpId() { return this.pId;}
	public String getstatus() { return this.status;}
	public institute getinsti() { return this.insti;}
	public int getrecTime() { return this.recTime;}
	
	
	public void setstatus() {this.status="Admitted";}
	public void setinsti(institute i) {this.insti=i;}
	public void setrecTime(int time) {this.recTime=time;}


	public Patient(String name,float temp,int oxy,int age)
	{
		this.name=name;
		this.age=age;
		this.bodyTemp=temp;
		this.oxyLevel=oxy;
		this.pId=++id;
	}
	
	public void pDetails()
	{
		System.out.println(this.name);
		System.out.println("Temperature is "+this.bodyTemp);
		System.out.println("Oxygen level is "+this.oxyLevel);
		System.out.println("Admission Status-"+this.status);
		if(this.status.equals("Not Admitted"));
		else System.out.println("Admitting Institute-"+(this.insti).getname());
		
		
	}
}
class institute
{
	private String name;
	private float temperature;
	private int oxygen;
	private int beds;
	private String status="OPEN";
	private List<Patient> instiPatient=new ArrayList<>();
	
	public institute(String name,float temp,int oxy,int beds)
	{
		this.name=name;
		this.temperature=temp;
		this.oxygen=oxy;
		this.beds=beds;
	}
	
	public String getname() { return this.name;}
	public float gettemperature() { return this.temperature;}
	public int getoxygen() { return this.oxygen;}
	public int getbeds() {return this.beds;}
	public String getstatus() {return this.status;}
	
	public void setbeds() {this.beds=this.beds-1;}
	public void setstatus() {this.status="CLOSED";}
	
	public void addPatient(Patient p) {this.instiPatient.add(p);}
	
	public void iDetails()
	{
		System.out.println(this.name);
		System.out.println("Temperature should be <= "+this.temperature);
		System.out.println("Oxygen levels should be >="+this.oxygen);
		System.out.println("Number of Available beds –"+this.beds);
		System.out.println("Admission status-"+this.status);
	}
	public void iPatient()
	{
		for(Patient p1: instiPatient)
		{
			System.out.println(p1.getname()+", recovery time is "+p1.getrecTime()+" days");
		}
	}
}
public class ap1
{
	List<Patient> allPatients=new ArrayList<>();
	List<Patient> nPatients=new ArrayList<>();
	List<institute> allInstis=new ArrayList<>();
	List<Patient> patientToRemove=new ArrayList<>();
	List<institute> instiToRemove=new ArrayList<>();
	void addInsti(institute insti,Scanner s)
	{
		
		for(Patient p1: allPatients)
		{
			if(insti.getbeds()==0) break;
			else
			{
				if(p1.getstatus().equals("Not Admitted") && p1.getoxyLevel()>=insti.getoxygen())
				{
					System.out.println("Recovery days for admitted patient ID "+p1.getpId()+":");
					p1.setrecTime(s.nextInt());
					insti.setbeds();
					insti.addPatient(p1);
					nPatients.remove(p1);
	
					p1.setstatus();
					p1.setinsti(insti);
					patientToRemove.add(p1);
				}
			}
		}
		for(Patient p1: allPatients)
		{
			if(insti.getbeds()==0) break;
			else
			{
				if(p1.getstatus().equals("Not Admitted") && p1.getbodyTemp()<=insti.gettemperature())
				{
					System.out.println("Recovery days for admitted patient ID "+p1.getpId()+":");
					p1.setrecTime(s.nextInt());
					p1.setstatus();
					p1.setinsti(insti);
					insti.setbeds();
					insti.addPatient(p1);
					nPatients.remove(p1);
					
					patientToRemove.add(p1);
				}
			}
		}
		if(insti.getbeds()==0)
		{
			instiToRemove.add(insti);
			insti.setstatus();
			
		}
		
		
	}
	
	public void deleteInsti()
	{
		for(institute i: instiToRemove) 
		{
		System.out.println(i.getname());		
		allInstis.remove(i);
		}
	    instiToRemove.clear();
	}
	public void deletePatients()
	{
		for(Patient p1:patientToRemove) 
		{
		System.out.println(p1.getpId());
		allPatients.remove(p1);
		}
	    patientToRemove.clear();
		
	}
	
public static void main(String[] args)
{
	Scanner s=new Scanner(System.in);
	ap1 app=new ap1();
	int n=s.nextInt();
	Patient p=null;
	for(int i=0;i<n;i++)
	{
		p=new Patient(s.next(),s.nextFloat(),s.nextInt(),s.nextInt());
		app.allPatients.add(p);
		app.nPatients.add(p);
	}
	
	while(app.nPatients.size()>0)
	{
		System.out.println("Query No: ");
		int q=s.nextInt();
		switch(q)
		{
		case 1:
		{
			String nm=s.next();
			System.out.println("Temperature Criteria-");
			float tmp=s.nextFloat();
			System.out.println("Oxygen Levels-");
			int ox=s.nextInt();
			System.out.println("Number of Available beds –");
			int bd=s.nextInt();
			institute newI=new institute(nm,tmp,ox,bd);
			app.allInstis.add(newI);
			newI.iDetails();
			app.addInsti(newI,s);
			break;
		}
		case 2:
		{
			System.out.println("Account ID removed of admitted patients ");
			app.deletePatients();
			break;
		}
		
		case 3:
		{
			System.out.println("Accounts removed of Institute whose admission is closed:");
			app.deleteInsti();
			break;
		}
		case 4:
		{
			System.out.println(app.nPatients.size()+" patients");
			break;
		}
		case 5:
		{   
			int count=0;
			for(institute i: app.allInstis) if((i.getstatus()).equals("OPEN")) count++;
			System.out.println(count+" institutes are admitting patients currently");
			break;
		}
		case 6:
		{
			String nam=s.next();
			boolean found=false;
			for(institute i:app.allInstis) 
				{
				if((i.getname()).equals(nam)) 
				{
				found=true;
				i.iDetails();
				break;
				}
				}
			if(!found)System.out.println("Invalid Query");
			break;
		}
		case 7:
		{
			int qId=s.nextInt();
			boolean found=false;
			for(Patient p1:app.allPatients) 
				{
				if(p1.getpId()==qId)
					{
					p1.pDetails();
					found=true;
					break;
					}
				}
			if(!found) System.out.println("Invalid query");
			break;
		}
		case 8:
		{
			for(Patient p1:app.allPatients) System.out.println(p1.getpId()+" "+p1.getname());
			break;
		}
		case 9:
		{
			String nam=s.next();
			boolean found=false;
			for(institute i:app.allInstis) 
				{
				if((i.getname()).equals(nam)) 
				{
					found=true;
					i.iPatient();
					break;
				}
				}
			if(!found)System.out.println("Invalid Query");
			break;
		}
		default:
		{
			System.out.println("Invalid Query");	
		}
			
		}
	}
}
}
