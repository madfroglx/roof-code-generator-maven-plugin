<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperFullName}">
    <sql id="columns">
        ${columns}
    </sql>
    <sql id="columns_as">
        ${columnsAs}
    </sql>
    <sql id="vals">
        ${vals}
    </sql>

    <sql id="conds">
        ${conds}
    </sql>
    <sql id="id_column">
        id
    </sql>
    <sql id="id_property">
        id
    </sql>
    <insert id="save" parameterType="${entityFullName}" useGeneratedKeys="true"
            keyProperty="id">
        <selectKey keyProperty="id" resultType="Long" order="AFTER">
            select last_insert_id()
        </selectKey>
        insert into ${tableName} (
        <include refid="columns"/>
        )
        values (
        <include refid="vals"/>
        )
    </insert>

    <delete id="delete">
        delete from ${tableName}
        where ${idCond}
    </delete>

    <delete id="deleteByExample">
        delete from ${tableName}
        where 1=1
        <include refid="conds"/>
    </delete>

    <update id="update">
        update ${tableName}
        <set>
            ${updateSet}
        </set>
        where ${idCond}
    </update>

    <update id="updateIgnoreNull">
        update ${tableName}
        <set>
            ${updateIgnoreNullSet}
        </set>
        where ${idCond}
    </update>

    <select id="load" resultType="${entityFullName}">
        select
        <include refid="columns_as"/>
        from ${tableName}
        where ${idCond}
    </select>

    <select id="select" resultType="${entityFullName}">
        select
        <include refid="columns_as"/>
        from ${tableName}
        where 1=1
        <include refid="conds"/>
    </select>

    <select id="selectForPage" resultType="${entityFullName}">
        select
        <include refid="columns_as"/>
        from ${tableName}
        where 1=1
        <include refid="conds"/>
        order by
        <include refid="id_column"/>
        desc
        <if test="firshtrownum != null and firshtrownum != 0 and limit != null and limit != 0">
            ${r'limit #{firstrownum} , #{limit}'}
        </if>
    </select>

    <select id="selectCount" resultType="java.lang.Integer">
        select
        count(id)
        from ${tableName}
        where 1=1
        <include refid="conds"/>
    </select>
</mapper>