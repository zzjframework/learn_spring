package com.dongz.codeutils.entitys.db;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 列对象
 */
@Data
@AllArgsConstructor
public class Column {
	/**
	 * 列名称
	 */
	private String columnName;
	/**
	 * 列名称(处理后的列名称)
	 */
	private String columnName2;
	/**
	 * java列类型
	 */
	private String columnType;
	/**
	 * 列数据库类型
	 */
	private String columnDbType;
	/**
	 * 列备注D
	 */
	private String columnComment;
	/**
	 * 是否是主键
	 */
	private String columnKey;

	private boolean isSelected;
	/**
	 * 外键关联表关系
	 */
	private List<Column.ForeignColumn> foreignColumn;

	@Data
	public static class ForeignColumn{
		private Table table;
		private Column foreignColumn;
	}
}
