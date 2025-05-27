package com.qzb.ai.mcp.server;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

/**
 * .
 *
 * @author <a href="qianzb@yxt.com">qianzb</a>
 * @date 2025/5/27 14:33
 * @since 1.0.0
 **/
@Service
public class CommandService {

    public static final String SYSTEM = "SYSTEM";

    public static final String WINDOWS = "windows";

    @Tool(description = "DAO生成")
    public String exeCommand(@ToolParam(description = "表名，多个使用分号区分，例如：sdcs_custom_dwd_config;sdcs_dwd_o2o_budget_type_apply_detail") String tables,
                             @ToolParam(description = "项目路径，例如：F:\\workspace\\2.0\\mixreport\\api") String filepath) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            if (ObjectUtils.isEmpty(System.getenv(SYSTEM)) || WINDOWS.equalsIgnoreCase(System.getenv(SYSTEM))) {
                String dir = filepath + "\\" + "datagen";
                processBuilder.directory(new File(dir));
                processBuilder.command("cmd.exe", "/c", "mvn mybatis:gen");
            } else {
                String dir = filepath + "/" + "datagen";
                processBuilder.command("cd " + dir);
                processBuilder.command("mvn mybatis:gen");
            }
            Process start = processBuilder.start();
            OutputStream outputStream = start.getOutputStream();
            outputStream.write(tables.getBytes());
            outputStream.flush();
            outputStream.close();
            start.waitFor(10, TimeUnit.SECONDS);
        } catch (Exception e) {
            return tables + "生成DAO失败，错误信息:" + e.getMessage();
        }
        return tables + "生成DAO成功";
    }

    public static void main(String[] args) {
        String s = new CommandService().exeCommand("sdcs_dwd_o2o_budget_type_apply_detail;sdcs_dwd_o2o_form_ext", "F:\\workspace\\2.0\\mixreport\\api");
        System.out.println(s);
    }

}
