package com.dongz.hrm.system.controllers;

import com.dongz.hrm.common.controllers.BaseController;
import com.dongz.hrm.common.entities.Result;
import com.dongz.hrm.domain.system.enums.PermissionStatus;
import com.dongz.hrm.domain.system.vos.PermissionVO;
import com.dongz.hrm.system.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dong
 * @date 2020/2/20 00:45
 * @desc
 */
@CrossOrigin
@RestController
@RequestMapping("/api/sys/permission")
public class PermissionController extends BaseController {

    @Autowired
    private PermissionService service;

    @GetMapping("/findAll")
    public Result findAll(
            Integer type,Long pid) {
        StringBuilder sb = new StringBuilder();
        sb.append("select t.* from permission t where 1=1");
        Map<String, Object> params = new HashMap<>();
        if (type != null) {
            sb.append(" and t.type = :type");
            params.put("type", type);
        }
        if (pid != null) {
            sb.append(" and t.pid = :pid");
            params.put("pid", pid);
        }
        List<Map<String, Object>> pageResult = this.queryForList(sb.toString(), params);
        return Result.SUCCESS(pageResult);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Long id) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        String sql = "select t.*,t.is_visible as isVisible from permission t where t.id = :id ";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        Map<String, Object> map = this.queryForObject(sql, params);
        PermissionStatus type = PermissionStatus.parse((Integer) map.get("type"));
        Class clazz = type.getClazz();
        StringBuilder sb = new StringBuilder();
        sb.append("select t.* from ")
                .append(type.getTableName())
                .append(" t where t.id = :id");
        Method me = clazz.getDeclaredMethod("getDataBaseMap", Object.class);
        Object o = this.queryForObject(sb.toString(), params, clazz);
        Map<String, Object> invoke = (Map<String, Object>) me.invoke(clazz.newInstance(),o);
        map.putAll(invoke);
        return Result.SUCCESS(map);
    }

    @PostMapping("/create")
    public Result create(@RequestBody PermissionVO vo) {
        service.create(vo);
        return Result.SUCCESS();
    }

    @PutMapping("/update")
    public Result update(@RequestBody PermissionVO vo) {
        service.update(vo);
        return Result.SUCCESS();
    }

    @DeleteMapping("/deleteById")
    public Result deleteById(@RequestParam Long id) {
        service.delete(id);
        return Result.SUCCESS();
    }
}