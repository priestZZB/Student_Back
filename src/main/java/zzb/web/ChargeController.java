package zzb.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import zzb.dto.Empty;
import zzb.dto.Result;
import zzb.entity.ClassInfo;
import zzb.entity.Messenger;
import zzb.entity.Problem;
import zzb.service.ChargeService;

import java.util.List;

@Controller
@RequestMapping("/charge")
public class ChargeController {
    @Autowired
    private ChargeService chargeService;



    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> addOneCharge(String chargeAccount, String chargePassword, String chargeName,  String email){
        return chargeService.addOneCharge(chargeAccount, chargePassword, chargeName,email);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> modifyOneMessenger(String chargeAccount, String chargePassword){
        System.out.println(chargeAccount);
        return chargeService.modifyOneCharge(chargeAccount, chargePassword);
    }

    @RequestMapping(value = "/modifyEmail", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> modifyMessengerEmail(String chargeAccount, String email){
        return chargeService.updateChargeEmail(chargeAccount, email);
    }

    @RequestMapping(value = "/getOrderNum", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<Problem>> getOrderNumProblem(String problemType, int pageIndex, int nums, String searchWord){
        return  chargeService.getOrderNumProblem(pageIndex, nums, problemType, searchWord);
    }

    @RequestMapping(value = "/getAllTeacher", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<Empty>> getAllSameTeacher(String problemType){
        return chargeService.getAllSameTeacher(problemType);
    }

    @RequestMapping(value = "/assignTeacher", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> assignTeacher(int problemId, String teacherAccount){
        System.out.println(teacherAccount);
        return chargeService.assignTeacher(problemId, teacherAccount);
    }

    @RequestMapping(value = "/reject", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> rejectToStudent(int problemId, String rejectReason){
        return chargeService.rejectToStudent(problemId, rejectReason);
    }

    @RequestMapping(value = "/getAllClass", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<ClassInfo>> getAllClass(){
        return chargeService.getAllClass();
    }

    @RequestMapping(value = "/addOneMessenger", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> addOneMessenger(int classId, String messengerAccount, String messengerPassword){
        return chargeService.addOneMessenger(classId, messengerAccount, messengerPassword);
    }

    @RequestMapping(value = "/deleteOneMessenger", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> destoryOneMessenger(String messengerAccount){
        return chargeService.detoryOneMessenger(messengerAccount);
    }

    @RequestMapping(value = "/getOneMessenger", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Messenger> getOneMessenger(String messengerAccount){
        return chargeService.getOneMessengerInfo(messengerAccount);
    }

    @RequestMapping(value = "/getOrderNumUnconfirm", method = RequestMethod.GET, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<List<Problem>> getOrderNumUnconfirmProblem(String problemType, int pageIndex, int nums, String searchWord){
        return  chargeService.getOrderNumUnconfirmProblem(pageIndex, nums, problemType, searchWord);
    }

    @RequestMapping(value = "/confirmProblem", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> confirmOneProblem(int problemId){
        return chargeService.confirmOneProblem(problemId);
    }

    @RequestMapping(value = "/toTeacher", method = RequestMethod.POST, produces = {
            "application/json; charset=utf-8" })
    @ResponseBody
    public Result<Empty> toTeacherProblem(int problemId, String toTeacherReason){
        return chargeService.toTeacherProblem(problemId, toTeacherReason);
    }
}
