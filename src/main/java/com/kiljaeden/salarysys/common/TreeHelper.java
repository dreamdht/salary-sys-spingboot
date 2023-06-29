//package com.kiljaeden.salarysys.common;
//
//import java.util.ArrayList;
//
///**
// * @Author: Kil'jaeden
// * @Email: pure3306@163.com
// * @Date: 2023/6/28 19:02
// * @Description:
// */
//public class TreeHelper {
//    //    /*构建Menu树形节点*/
//    public static List<SysMenu> buildTree(List<SysMenu> sysMenuList){
//        /*创建集合封装最终数据*/
//        List<SysMenu> trees = new ArrayList<>();
//        /*遍历所有集合*/
//        for (SysMenu sysMenu : sysMenuList) {
//            /*找到递归入口,parent_id = 0*/
//            if(sysMenu.getParentId() == 0){
//                trees.add(findChildNode(sysMenu,sysMenuList));
//            }
//        }
//        return trees;
//    }
//    //
////    /*
////    * 递归子方法
////    * 如果 id==parentId，那么将遍历结果封装
////    * */
//    public static SysMenu findChildNode(SysMenu sysMenu,List<SysMenu> treeNodes){
//        /*数据初始化*/
//        sysMenu.setChildren(new ArrayList<SysMenu>());
//        /*遍历递归查找*/
//        for (SysMenu menu : treeNodes) {
//            String id = sysMenu.getId();
//            long cid = Long.parseLong(id);
//            Long parentId = menu.getParentId();
//            if(cid==parentId){
//                if(sysMenu.getChildren()==null){
//                    sysMenu.setChildren(new ArrayList<>());
//                }
//                sysMenu.getChildren().add(findChildNode(menu,treeNodes));
//            }
//        }
//        return sysMenu;
//    }
//}
