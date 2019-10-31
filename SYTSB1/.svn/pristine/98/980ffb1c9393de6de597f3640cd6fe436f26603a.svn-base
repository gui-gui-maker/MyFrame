package com.khnt.common;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.ToListResultTransformer;

final public class Transformers {

	private Transformers() {}
	
	/**
	 * Each row of results is a <tt>Map</tt> from alias to values/entities
	 */
	public static final AliasToEntityMapResultTransformer ALIAS_TO_ENTITY_MAP =
			AliasToEntityMapResultTransformer.INSTANCE;

	/**
	 * Each row of results is a <tt>List</tt> 
	 */
	public static final ToListResultTransformer TO_LIST = ToListResultTransformer.INSTANCE;
	
	/**
	 * Creates a resulttransformer that will inject aliased values into 
	 * instances of Class via property methods or fields.
	 */
	public static ResultTransformer aliasToBean(Class target) {
		return new AliasToBeanResultTransformer(target);
	}
	
}