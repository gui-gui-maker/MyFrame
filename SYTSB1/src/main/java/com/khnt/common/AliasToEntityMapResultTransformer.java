package com.khnt.common;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.transform.BasicTransformerAdapter;

public class AliasToEntityMapResultTransformer extends BasicTransformerAdapter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final AliasToEntityMapResultTransformer INSTANCE = new AliasToEntityMapResultTransformer();

	/**
	 * Disallow instantiation of AliasToEntityMapResultTransformer.
	 */
	private AliasToEntityMapResultTransformer() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Map result = new HashMap(tuple.length);
		for ( int i=0; i<tuple.length; i++ ) {
			String alias = aliases[i];
			if ( alias!=null ) {
				result.put( alias.toUpperCase(), tuple[i] );
			}
		}
		return result;
	}

	/**
	 * Serialization hook for ensuring singleton uniqueing.
	 *
	 * @return The singleton instance : {@link #INSTANCE}
	 */
	private Object readResolve() {
		return INSTANCE;
	}
}
