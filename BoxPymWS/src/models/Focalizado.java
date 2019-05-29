package models;

import java.io.Serializable;

public class Focalizado implements Serializable{

	private static final long serialVersionUID = 2803296492556746868L;

	private String 	mes_sige;
	private String	run;
	private String	sal_dgv;
	private String	nombres;
	private String	ap_pat;
	private String	ap_mat;
	
	/**********************************************************************************************************************/
	
	public Focalizado() {}
	
	public Focalizado(String mes_sige, String run, String sal_dgv, String nombres, String ap_pat, String ap_mat) {
		super();
		this.mes_sige 	= 	mes_sige;
		this.run 		= 	run;
		this.sal_dgv 	= 	sal_dgv;
		this.nombres 	= 	nombres;
		this.ap_pat 	=	ap_pat;
		this.ap_mat 	= 	ap_mat;
	}
	
	/**********************************************************************************************************************/

	public String getMes_sige() {
		return mes_sige;
	}
	public void setMes_sige(String mes_sige) {
		this.mes_sige = mes_sige;
	}
	public String getRun() {
		return run;
	}
	public void setRun(String run) {
		this.run = run;
	}
	public String getSal_dgv() {
		return sal_dgv;
	}
	public void setSal_dgv(String sal_dgv) {
		this.sal_dgv = sal_dgv;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getAp_pat() {
		return ap_pat;
	}
	public void setAp_pat(String ap_pat) {
		this.ap_pat = ap_pat;
	}
	public String getAp_mat() {
		return ap_mat;
	}
	public void setAp_mat(String ap_mat) {
		this.ap_mat = ap_mat;
	}	
}