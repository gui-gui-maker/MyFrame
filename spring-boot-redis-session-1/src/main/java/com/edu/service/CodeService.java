package com.edu.service;

import java.util.List;

import com.edu.bean.CodeJhlb;
import com.edu.bean.CodeJhxz;
import com.edu.bean.CodeKl;
import com.edu.bean.CodePc;
import com.edu.bean.CodeZklx;

public interface CodeService {

	List<CodePc> getPcdmByNf(int nf) throws Exception;

	List<CodeKl> getKlByNf(int nf) throws Exception;

	List<CodeJhxz> getJhxzByNf(int nf) throws Exception;

	List<CodeJhlb> getJhlbByNf(int nf) throws Exception;

	List<CodeZklx> getZklxByNf(int nf) throws Exception;

}
