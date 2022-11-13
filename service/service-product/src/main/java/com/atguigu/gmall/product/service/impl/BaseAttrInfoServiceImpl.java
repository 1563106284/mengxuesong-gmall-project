package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.product.domain.BaseAttrValue;
import com.atguigu.gmall.product.mapper.BaseAttrValueMapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.gmall.product.domain.BaseAttrInfo;
import com.atguigu.gmall.product.service.BaseAttrInfoService;
import com.atguigu.gmall.product.mapper.BaseAttrInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mengxueshong
 * @description 针对表【base_attr_info(属性表)】的数据库操作Service实现
 * @createDate 2022-11-06 21:15:10
 */
@Service
public class BaseAttrInfoServiceImpl extends ServiceImpl<BaseAttrInfoMapper, BaseAttrInfo>
        implements BaseAttrInfoService {

    @Autowired
    BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    BaseAttrValueMapper baseAttrValueMapper;

    /**
     * 2: 保存 平台属性
     *
     * @param baseAttrInfo
     */
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {

        /**
         *  优化：新增 和修改使用的是一个接口
         */

        //0.91:删除不带id的平台属性值：
        // 1:通过id 判断 是新增 还是修改
        if (baseAttrInfo.getId() == null) {
            // 1.1 id为null 新增
            insertBaeseAttr(baseAttrInfo);
        } else {
            // 就是修改属性
            updateBaseAttr(baseAttrInfo);
        }

    }

    private void updateBaseAttr(BaseAttrInfo baseAttrInfo) {
        // 1.2 id不是null 修改
        // 1.21:  先删除：
        List<BaseAttrValue> valueList = baseAttrInfo.getAttrValueList();
        // 收集 属性值的id
        List<Long> vids = new ArrayList();
        for (BaseAttrValue attrValue : valueList) {
            Long id = attrValue.getId();
            if (id != null) {
                vids.add(id);
            }
            // 再次判断 ：有值在删除
            if (vids.size() > 0) {
                // 部分删除
                QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper();
                deleteWrapper.eq("attr_id", baseAttrInfo.getId());
                deleteWrapper.notIn("id", vids);
                baseAttrValueMapper.delete(deleteWrapper);
            } else {
                // 全部删除
                QueryWrapper<BaseAttrValue> deleteWrapper = new QueryWrapper<>();
                deleteWrapper.eq("attr_id", baseAttrInfo.getId());
                baseAttrValueMapper.delete(deleteWrapper);
            }
        }

        // 1.2: 修改平台属性info 名相关信息
        baseAttrInfoMapper.updateById(baseAttrInfo);
        // 1.3: 修改值：
        for (BaseAttrValue attrValue : valueList) {
            if (attrValue.getId() == null) {
                // 1.4:如果attr value为null 新增
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insert(attrValue);
            }
            if (attrValue.getId() != null) {
                // 1.5:如果attr value id 不为空 就是修改
                baseAttrValueMapper.updateById(attrValue);
            }
        }
    }

    /**
     * 2: 新增平台属性
     *
     * @param baseAttrInfo
     */
    private void insertBaeseAttr(BaseAttrInfo baseAttrInfo) {
        // 1:保存平台名信息
        baseAttrInfoMapper.insert(baseAttrInfo);
        // 2: 保存平台属性值信息：
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        // 2.1:拿到attr id 回填新增用
        Long id = baseAttrInfo.getId();
        for (BaseAttrValue value : attrValueList) {
            value.setAttrId(id);
            baseAttrValueMapper.insert(value);
        }
    }

    /**
     * 1:根据分类属性 查询平台属性
     *
     * @param c1Id
     * @param c2Id
     * @param c3Id
     * @return
     */
    @Override
    public List<BaseAttrInfo> byCategoryGetBaseAttrInfo(String c1Id, String c2Id, String c3Id) {
        List<BaseAttrInfo> list = baseAttrInfoMapper.byCategoryGetBaseAttrInfo(c1Id, c2Id, c3Id);
        return list;
    }
}




