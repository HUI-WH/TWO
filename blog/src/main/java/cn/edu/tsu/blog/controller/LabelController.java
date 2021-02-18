package cn.edu.tsu.blog.controller;

import cn.edu.tsu.blog.commons.code.MsgCode;
import cn.edu.tsu.blog.commons.dto.LabelDto;
import cn.edu.tsu.blog.commons.dto.ResponseResult;
import cn.edu.tsu.blog.model.Label;
import cn.edu.tsu.blog.model.Labelclass;
import cn.edu.tsu.blog.service.LabelService;
import cn.edu.tsu.blog.service.LabelclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class LabelController {

    @Autowired
    private LabelclassService labelclassService;
    @Autowired
    private LabelService labelService;

/*******************************   Label  *************************************************/
    @PostMapping("label/create")
    private ResponseResult<?> createLabel(@RequestBody Label label){

        int result = labelService.insert(label);

        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"添加成功",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"操作失败",result);
        }
    }

    @GetMapping("label/delete/{id}")
    private ResponseResult<?> deleteLabel(@PathVariable Integer id){
        int delete = labelService.delete(id);
        if(delete>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"删除成功",delete);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"删除失败",delete);
        }
    }

    @PostMapping("label/update")
    public ResponseResult<?> updateLabel(@RequestBody Label label){
        int result = labelService.update(label);
        if(result>0){
            return new ResponseResult<>(MsgCode.SUCCESS,"更新成功",result);
        }else {
            return new ResponseResult<>(MsgCode.FAILED,"更新失败",result);
        }
    }

    @GetMapping("label/list")
    public ResponseResult<List<Label>> getList(){

//        List<LabelDto> dtoList = new ArrayList<>();
//        List<Labelclass> labelClasses = labelclassService.selectAll();
        List<Label> labels = labelService.selectAll();
//
//        for(Labelclass lClass:labelClasses){
//            LabelDto lDto =new LabelDto();
//            lDto.setId(lClass.getId());
//            lDto.setName(lClass.getName());
//            lDto.setChildren(new ArrayList<>());
//            dtoList.add(lDto);
//        }
//        for(Label l:labels){
//            for(LabelDto dto:dtoList){
//                if(l.getLabelClass().equals(dto.getId())){
//                    dto.getChildren().add(l);
//                }
//            }
//        }
        return new ResponseResult<>(MsgCode.SUCCESS,"查询成功",labels);
    }

/*************************************** Label Class*****************************************************/
   @PostMapping("labelClass/create")
   public ResponseResult<?> createClass(@RequestBody Labelclass labelclass){

       int result = labelclassService.insertClass(labelclass);
       if(result>0){
           return new ResponseResult<>(MsgCode.SUCCESS,"创建成功",result);
       }else {
           return new ResponseResult<>(MsgCode.FAILED,"创建失败",result);
       }
   }

   @GetMapping("labelClass/delete/{id}")
   public ResponseResult<?> deleteClass(@PathVariable Integer id){
       int result = labelclassService.deleteClass(id);
       // TODO 级联删除
       if(result>0){
           return new ResponseResult<>(MsgCode.SUCCESS,"删除成功",result);
       }else {
           return new ResponseResult<>(MsgCode.FAILED,"删除失败",result);
       }
   }

   @PostMapping("labelClass/update")
   public ResponseResult<?> updateClass(Labelclass labelclass){

       int result = labelclassService.updateClass(labelclass);
       if(result>0){
           return new ResponseResult<>(MsgCode.SUCCESS,"更新成功",result);
       }else {
           return new ResponseResult<>(MsgCode.FAILED,"更新失败",result);
       }
   }

   @GetMapping("labelClass/list")
   public ResponseResult<List<Labelclass>> getClassList(){

       List<Labelclass> labelclasses = labelclassService.selectAll();

       return new ResponseResult<>(MsgCode.SUCCESS,"success",labelclasses);

   }

}
