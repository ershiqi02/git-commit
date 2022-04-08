package com.demo.security;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author: 郑嘉磊
 * @Date: 2022/3/26 14:48
 */
public class AutoCode {
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG=new DataSourceConfig.Builder(
            "jdbc:mysql://124.221.103.52:3333/security?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT",
            "root",
            "zhengjialei"
    );
    public static void main(String[] args) {
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                //全局配置
                .globalConfig((scanner,builder) ->
                        builder.author((scanner.apply("请输入作者名")))
                                .outputDir(System.getProperty("user.dir")+"\\src\\main\\java")
                                //覆盖文件
                                .fileOverride()
                                .disableOpenDir()
                )

                //包配置
                .packageConfig(builder ->
                        builder.parent("com.demo.security")
                                .entity("entity")
                                .service("service")
                                .serviceImpl("serviceImpl")
                                .controller("controller")
                                .mapper("mapper")
                                .xml("mapper")
                                .pathInfo(Collections.singletonMap(OutputFile.mapper.xml, System.getProperty("user.dir") + "src/main/resources/mapper")))

                //策略配置
                .strategyConfig((scanner,builder) ->
                        builder.addInclude(getTables(scanner.apply("请输入表名，多个表名用，隔开")))
                                .controllerBuilder().enableRestStyle()
                                .enableHyphenStyle().entityBuilder().enableLombok()
                                .addTableFills(new Column("created", FieldFill.INSERT)
                                )
                                .build())
                //执行
                .execute();
    }
    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
