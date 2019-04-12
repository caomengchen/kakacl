package cn.stylefeng.guns.modular.store.dao;

import cn.stylefeng.guns.modular.store.model.AccountIdCard;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wangwei
 * @since 2018-11-30
 */
public interface AccountIdCardMapper extends BaseMapper<AccountIdCard> {

	int changeAccountId(@Param("id") Long id, @Param("accountId") Long accountId);
}
