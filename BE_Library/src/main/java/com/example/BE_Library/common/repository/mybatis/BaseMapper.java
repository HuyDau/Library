package com.example.BE_Library.common.repository.mybatis;

public interface BaseMapper {
    String SORT_AND_PAGING_CONDITION = """
            <if test='query.getSort() != null'>
                ORDER BY
                <foreach item='value' index='key' collection='query.getSort()' open='' separator=',' close=''>
                    ${key} ${value}
                </foreach>
            </if>
            <if test='limit != null'>
                LIMIT #{limit}
            </if>
            <if test='offset != null'>
                OFFSET #{offset}
            </if>
        """;
}
