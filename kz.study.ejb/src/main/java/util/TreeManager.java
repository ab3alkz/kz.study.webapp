/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

public class TreeManager {
    /*    
    public static List<GsonTreeData> getTree(List<GsonTreeData> list) {
        
        List<GsonTreeData> tree = new ArrayList<>();
        for (GsonTreeData item : list) {
            
            GsonTreeData treeData = new GsonTreeData();
            
            if (item.getParid() == null || item.getParid() == 0) {
                treeData.setId(item.getId());
                treeData.setParid(item.getParid());
                treeData.setValue(item.getValue());
                treeData.setCode(item.getCode());
                treeData.setOpen(item.getOpen());
                                
                List<GsonTreeData> childs = getChild(list, item.getId());
                
                if (childs.size() > 0)
                    treeData.setData(childs);
                
                tree.add(treeData);
            }            
        }

        return tree;
    }
        
    private static List<GsonTreeData> getChild(List<GsonTreeData> list, Long id) {
        List<GsonTreeData> childs = new ArrayList<>();
        
        for (GsonTreeData item : list) {
            if (item.getParid() != null && item.getParid().equals(id))
            {
                GsonTreeData treeData = new GsonTreeData();
                treeData.setId(item.getId());
                treeData.setParid(item.getParid());
                treeData.setValue(item.getValue());
                                
                List<GsonTreeData> subchilds = getChild(list, item.getId());
                
                if (subchilds.size() > 0)
                    treeData.setData(subchilds);
                
                childs.add(treeData);
            }
        }
        
        return childs;
    }
    
    public static List<GsonTreeData> appendRootNode(List<GsonTreeData> list, String value)
    {
        GsonTreeData rootData = new GsonTreeData();        
        rootData.setId(Long.MIN_VALUE);
        rootData.setParid(Long.MIN_VALUE);
        rootData.setOpen(Boolean.TRUE);
        rootData.setValue(value);
        rootData.setData(list);
        
        List<GsonTreeData> tree = new ArrayList<>();
        tree.add(rootData);
        
        return tree;
    }     
    */
}
