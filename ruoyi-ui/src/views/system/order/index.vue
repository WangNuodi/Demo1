<template>
  <div class="app-container">
    <el-row :gutter="20">
      <!--部门数据-->
<!--      <el-col :span="4" :xs="24">-->
<!--        <div class="head-container">-->
<!--          <el-input-->
<!--            v-model="deptName"-->
<!--            placeholder="请输入部门名称"-->
<!--            clearable-->
<!--            size="small"-->
<!--            prefix-icon="el-icon-search"-->
<!--            style="margin-bottom: 20px"-->
<!--          />-->
<!--        </div>-->
<!--        <div class="head-container">-->
<!--          <el-tree-->
<!--            :data="deptOptions"-->
<!--            :props="defaultProps"-->
<!--            :expand-on-click-node="false"-->
<!--            :filter-node-method="filterNode"-->
<!--            ref="tree"-->
<!--            node-key="id"-->
<!--            default-expand-all-->
<!--            highlight-current-->
<!--            @node-click="handleNodeClick"-->
<!--          />-->
<!--        </div>-->
<!--      </el-col>-->
      <!--交易单数据-->
<!--      <el-col :span="20" :xs="24">-->
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="交易码" prop="tradeCode">
            <el-input
              v-model="queryParams.tradeCode"
              placeholder="请输入交易码"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>

<!--          <el-form-item label="商品类别" prop="goodsKind">-->
<!--            <el-input-->
<!--              v-model="queryParams.goodsKind"-->
<!--              placeholder="请输入商品类别 1虚拟商品 2实物商品"-->
<!--              clearable-->
<!--              style="width: 240px"-->
<!--              @keyup.enter.native="handleQuery"-->
<!--            />-->
<!--          </el-form-item>-->

          <el-form-item label="商品类别" prop="goodsKind">
            <el-select
              v-model="queryParams.goodsKind"
              placeholder="请选择商品类别"
              clearable
              style="width: 240px"
            >
              <el-option
                v-for="dict in goodsKindDict"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>

          <el-form-item label="状态" prop="status">
            <el-select
              v-model="queryParams.status"
              placeholder="交易单状态"
              clearable
              style="width: 240px"
            >
            <el-option
              v-for="dict in allstatus"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
            </el-select>
          </el-form-item>

<!--          <el-form-item label="收到付款" prop="recive">-->
<!--            <el-select-->
<!--              v-model="queryParams.recive"-->
<!--              placeholder="请选择是否收到付款"-->
<!--              clearable-->
<!--              style="width: 240px"-->
<!--            >-->
<!--              <el-option-->
<!--                v-for="dict in yesOrNo"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="是否发货" prop="send">-->
<!--            <el-select-->
<!--              v-model="queryParams.send"-->
<!--              placeholder="请选择是否发货"-->
<!--              clearable-->
<!--              style="width: 240px"-->
<!--            >-->
<!--              <el-option-->
<!--                v-for="dict in sendYesOrNo"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->

<!--          <el-form-item label="是否收货" prop="define">-->
<!--            <el-select-->
<!--              v-model="queryParams.define"-->
<!--              placeholder="请选择是否收货"-->
<!--              clearable-->
<!--              style="width: 240px"-->
<!--            >-->
<!--              <el-option-->
<!--                v-for="dict in defineYesOrNo"-->
<!--                :key="dict.value"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              />-->
<!--            </el-select>-->
<!--          </el-form-item>-->

          <el-form-item label="创建时间">
            <el-date-picker
              v-model="dateRange"
              style="width: 240px"
              value-format="yyyy-MM-dd"
              type="daterange"
              range-separator="-"
              start-placeholder="开始日期"
              end-placeholder="结束日期"
            ></el-date-picker>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
              v-hasPermi="['system:order:add']"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="success"
              plain
              icon="el-icon-edit"
              size="mini"
              :disabled="single"
              @click="handleUpdate2"
              v-hasPermi="['system:order:edit']"
            >修改</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              type="danger"
              plain
              icon="el-icon-delete"
              size="mini"
              :disabled="multiple"
              @click="handleDelete"
              v-hasPermi="['system:order:remove']"
            >删除</el-button>
          </el-col>
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="info"-->
<!--              plain-->
<!--              icon="el-icon-upload2"-->
<!--              size="mini"-->
<!--              @click="handleImport"-->
<!--              v-hasPermi="['system:order:import']"-->
<!--            >导入</el-button>-->
<!--          </el-col>-->
<!--          <el-col :span="1.5">-->
<!--            <el-button-->
<!--              type="warning"-->
<!--              plain-->
<!--              icon="el-icon-download"-->
<!--              size="mini"-->
<!--              @click="handleExport"-->
<!--              v-hasPermi="['system:order:export']"-->
<!--            >导出</el-button>-->
<!--          </el-col>-->
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="50" align="center" />
<!--          <el-table-column label="交易单id" align="center" key="orderId" prop="orderId" v-if="columns[0].visible" />-->
          <el-table-column label="交易码" align="center" key="tradeCode" prop="tradeCode" v-if="columns[5].visible" />
<!--          <el-table-column label="用户编号" align="center" key="userId" prop="userId" v-if="columns[1].visible" />-->
<!--          <el-table-column label="商品类别" align="center" key="goodsKind" v-if="columns[2].visible" >-->
<!--            <template slot-scope="scope">-->
<!--              <span  v-if="scope.row.goodskind==1">虚拟</span>-->
<!--              <span v-else>实物</span>-->
<!--            </template>-->
<!--            <el-table-column/>-->
          <el-table-column label="商品类别" align="center" prop="goodsKind" v-if="columns[6].visible" width="160">
            <template slot-scope="scope">
<!--              <el-option-->
<!--                v-for="dict in spar"-->
<!--                :key="dict.key"-->
<!--                :label="dict.label"-->
<!--                :value="dict.value"-->
<!--              ></el-option>-->
              <span >{{scope.row.goodsKind==1?'虚拟商品':'实物商品'}}</span>
            </template>
          </el-table-column>
<!--          <el-table-column label="商品类别" align="center" key="goodsKind" prop="goodsKind" v-if="columns[2].visible"  />-->
<!--          <template slot-scope="scope">-->
<!--            <dict-tag :options="dict.type.sys_order_goods_kind" :value="scope.row.goodsKind"/>-->
<!--          </template>-->
          <el-table-column label="商品名称" align="center" key="goodsName" prop="goodsName" v-if="columns[3].visible" />
          <el-table-column label="收款金额" align="center" key="tradeMoney" prop="tradeMoney" v-if="columns[4].visible"  />
          <el-table-column label="担保费" align="center" key="tradeMoneyService" prop="tradeMoneyService" v-if="columns[5].visible"  />
          <el-table-column label="收款方支付金额" align="center" key="tradeMoneyMe" prop="tradeMoneyMe" v-if="columns[6].visible" :show-overflow-tooltip="true" />
          <el-table-column label="付款方支付金额" align="center" key="tradeMoneyYou" prop="tradeMoneyYou" v-if="columns[7].visible" :show-overflow-tooltip="true" />
<!--          <el-table-column label="状态" align="center" key="status" v-if="columns[8].visible">-->
<!--            <template slot-scope="scope">-->
<!--              <el-switch-->
<!--                v-model="scope.row.status"-->
<!--                active-value="0"-->
<!--                inactive-value="1"-->
<!--                @change="handleStatusChange(scope.row)"-->
<!--              ></el-switch>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column label="交易单状态" align="center" prop="status" v-if="columns[8].visible" width="160">
            <template slot-scope="scope">
<!--                            <span-->
<!--                              v-for="dict in allstatus"-->
<!--                              :key="dict.key"-->
<!--                              :label="dict.label"-->
<!--                              :value="dict.value"-->
<!--                            ></span>-->
          <span >{{scope.row.status==0?'交易进行中':scope.row.status==1?'商家已确认收款':scope.row.status==2?'收款方已发货':scope.row.status==3?'付款方已收货,交易完成':scope.row.status==4?'商家确认打款':scope.row.status==5?'取消交易':scope.row.status==6?'拒绝收货':''}}</span>
<!--              <span >{{scope.row.status=='0'?'交易进行中':''}}</span>-->
<!--              <span >{{scope.row.status==1?'商家已确认收款':''}}</span>-->
<!--              <span >{{scope.row.status==2?'收款方已发货':''}}</span>-->
<!--              <span >{{scope.row.status==3?'付款方已收货,交易完成':''}}</span>-->
<!--              <span >{{scope.row.status==4?'商家确认打款':''}}</span>-->
<!--              <span >{{scope.row.status==5?'取消交易':''}}</span>&ndash;&gt;-->
            </template>
          </el-table-column>
          <el-table-column label="商家确认收款" align="center" key="status" v-if="columns[8].visible">
            <template slot-scope="scope" >
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '0' || scope.row.status == '1'"
                active-value="1"
                inactive-value="0"
                @change="handleStatusChangeReceive(scope.row)"
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '2'"
                active-value="2"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '3'"
                active-value="3"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '4'"
                active-value="4"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '5'"
                active-value="5"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '6'"
                active-value="6"
                disabled
              ></el-switch>
            </template>
          </el-table-column>
          <el-table-column label="拒绝收货" align="center" key="status" v-if="columns[8].visible">
            <template slot-scope="scope" >
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '6' || scope.row.status == '2'"
                active-value="6"
                inactive-value="2"
                @change="handleStatusChange4(scope.row)"
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '0'"
                active-value="1"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '1'"
                active-value="2"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '3'"
                active-value="4"
                disabled
              ></el-switch>
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '4'"
                active-value="5"
                disabled
              ></el-switch>
<!--              <el-switch-->
<!--                v-model="scope.row.status"-->
<!--                v-if="scope.row.status == '3'"-->
<!--                active-value="3"-->
<!--                disabled-->
<!--              ></el-switch>-->
              <el-switch
                v-model="scope.row.status"
                v-if="scope.row.status == '5'"
                active-value="6"
                disabled
              ></el-switch>
            </template>
          </el-table-column>
<!--          <el-table-column label="是否发货" align="center" key="send" v-if="columns[8].visible">-->
<!--            <template slot-scope="scope">-->
<!--              <el-switch-->
<!--                v-model="scope.row.send"-->
<!--                active-value="0"-->
<!--                inactive-value="1"-->
<!--                @change="handleStatusChangeSend(scope.row)"-->
<!--              ></el-switch>-->
<!--            </template>-->
<!--          </el-table-column>-->
<!--          <el-table-column label="是否收货" align="center" key="define" v-if="columns[8].visible">-->
<!--            <template slot-scope="scope">-->
<!--              <el-switch-->
<!--                v-model="scope.row.define"-->
<!--                active-value="0"-->
<!--                inactive-value="1"-->
<!--                @change="handleStatusChangeDefine(scope.row)"-->
<!--              ></el-switch>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-table-column label="创建时间" align="center" prop="createTime" v-if="columns[6].visible" width="160">
            <template slot-scope="scope">
              <span>{{ parseTime(scope.row.createTime) }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="操作"
            align="center"
            width="160"
            class-name="small-padding fixed-width"
          >
<!--            <template slot-scope="scope" v-if="scope.row.userId !== 1">-->
              <template slot-scope="scope" >
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:order:edit']"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
                v-hasPermi="['system:order:remove']"
              >删除</el-button>
<!--              <el-dropdown size="mini" @command="(command) => handleCommand(command, scope.row)" v-hasPermi="['system:order:resetPwd', 'system:order:edit']">-->
<!--                <el-button size="mini" type="text" icon="el-icon-d-arrow-right">更多</el-button>-->
<!--                <el-dropdown-menu slot="dropdown">-->
<!--                  <el-dropdown-item command="handleResetPwd" icon="el-icon-key"-->
<!--                    v-hasPermi="['system:order:resetPwd']">重置密码</el-dropdown-item>-->
<!--                  <el-dropdown-item command="handleAuthRole" icon="el-icon-circle-check"-->
<!--                    v-hasPermi="['system:order:edit']">分配角色</el-dropdown-item>-->
<!--                </el-dropdown-menu>-->
<!--              </el-dropdown>-->
            </template>
          </el-table-column>
        </el-table>


        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
<!--      </el-col>-->
    </el-row>

    <!-- 添加或修改交易单配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="12">
<!--            <el-form-item v-if="form.userId == undefined" label="用户名称" prop="userName">-->
            <el-form-item label="用户id" prop="userId">
              <el-input v-model="form.userId" placeholder="请输入用户id" maxlength="30" />
            </el-form-item>
          </el-col>
<!--          <el-col :span="12">-->
<!--&lt;!&ndash;            <el-form-item v-if="form.userId == undefined" label="用户密码" prop="password">&ndash;&gt;-->
<!--            <el-form-item  label="放款规则" prop="tradeRule">-->
<!--              <el-input v-model="form.tradeRule" placeholder="请输入放款规则"  maxlength="20"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
          <el-col :span="12">
<!--            <el-form-item label="状态">-->
<!--              <el-radio-group v-model="form.status">-->
<!--                <el-radio-->
<!--                  v-for="dict in dict.type.sys_normal_disable"-->
<!--                  :key="dict.value"-->
<!--                  :label="dict.value"-->
<!--                >{{dict.label}}</el-radio>-->
<!--              </el-radio-group>-->
<!--            </el-form-item>-->

            <el-form-item label="状态">
              <el-select v-model="form.status" placeholder="交易单状态">
                <el-option
                  v-for="dict in allstatus"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>

          </el-col>
        </el-row>
        <el-row>
<!--          <el-col :span="12">-->
<!--            <el-form-item label="商品类别" prop="goodsKind">-->
<!--              <el-input v-model="form.goodsKind" placeholder="请输入商品类别" maxlength="11" />-->
<!--            </el-form-item>-->
<!--          </el-col>-->
                    <el-col :span="12">
                      <el-form-item label="商品类别">
                        <el-select v-model="form.goodsKind" placeholder="请选择商品类别">
                          <el-option
                            v-for="dict in spar"
                            :key="dict.key"
                            :label="dict.label"
                            :value="dict.value"
                          ></el-option>
                        </el-select>
                      </el-form-item>
                    </el-col>
<!--          <el-table-column label="商品类别" align="center" prop="createTime" v-if="columns[6].visible" width="160">-->
<!--            <template slot-scope="scope">-->
<!--              <span  v-if="scope.row.goodskind==1">虚拟</span>-->
<!--              <span v-else>实物</span>-->
<!--            </template>-->
<!--          </el-table-column>-->
          <el-col :span="12">
            <el-form-item label="商品名称" prop="goodsName">
              <el-input v-model="form.goodsName" placeholder="请输入商品名称" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="担保费" prop="tradeMoneyService">
              <el-input v-model="form.tradeMoneyService" placeholder="请输入担保服务费" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收款金额" prop="tradeMoney">
              <el-input v-model="form.tradeMoney" placeholder="请输入收款金额" maxlength="30" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收款方支付地址" prop="tradeAddress"  maxlength="50">
              <el-input v-model="form.tradeAddress" placeholder="请输入收款方支付地址" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="收款方支付金额" prop="tradeMoneyMe">
              <el-input v-model="form.tradeMoneyMe" placeholder="请输入收款方支付金额" maxlength="11" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="付款方支付金额" prop="tradeMoneyYou">
              <el-input v-model="form.tradeMoneyYou" placeholder="请输入付款方支付金额" maxlength="11" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="付款方密码" prop="tradePasswordYou">
              <el-input v-model="form.tradePasswordYou" placeholder="请输入付款方密码" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="收款密码" prop="tradePassword">
              <el-input v-model="form.tradePassword" placeholder="请输入收款密码" maxlength="50" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="交易码" prop="tradeCode">
              <el-input v-model="form.tradeCode" placeholder="请输入交易码或者系统生成" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="交易网址" prop="tradeUrl">
              <el-input v-model="form.tradeUrl" placeholder="请输入交易网址或者系统生成" maxlength="50" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="放款规则">
              <el-input v-model="form.tradeRule" type="textarea" placeholder="请输入放款规则"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 交易单导入对话框 -->
    <el-dialog :title="upload.title" :visible.sync="upload.open" width="400px" append-to-body>
      <el-upload
        ref="upload"
        :limit="1"
        accept=".xlsx, .xls"
        :headers="upload.headers"
        :action="upload.url + '?updateSupport=' + upload.updateSupport"
        :disabled="upload.isUploading"
        :on-progress="handleFileUploadProgress"
        :on-success="handleFileSuccess"
        :auto-upload="false"
        drag
      >
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        <div class="el-upload__tip text-center" slot="tip">
          <div class="el-upload__tip" slot="tip">
            <el-checkbox v-model="upload.updateSupport" /> 是否更新已经存在的交易单数据
          </div>
          <span>仅允许导入xls、xlsx格式文件。</span>
          <el-link type="primary" :underline="false" style="font-size:12px;vertical-align: baseline;" @click="importTemplate">下载模板</el-link>
        </div>
      </el-upload>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFileForm">确 定</el-button>
        <el-button @click="upload.open = false">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listUser, getUser, delUser, addUser, updateUser, resetUserPwd, changeUserStatus, deptTreeSelect } from "@/api/system/user";
import { list, getOrder,  addOrder, updateOrder, delOrder , getUser, getListOne,changeOrderStatus,changeOrderrecive,changeOrdersend,changeOrderdefine } from "@/api/system/user";
import { getToken } from "@/utils/auth";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";;

export default {
  name: "User",
  dicts: ['sys_normal_disable', 'sys_user_sex' ,'sys_order_goods_kind'],
  components: { Treeselect },
  data() {
    return {
      // 遮罩层
      loading: true,

        spar:[
            { key: 1, label: '虚拟商品', value: 1 },
            { key: 2, label: '实物商品', value: 2 }
        ],
        allstatus:[
            { key: 0, label: '交易进行中', value: 0 },
            { key: 1, label: '商家已确认收款', value: 1 },
            { key: 2, label: '收款方已发货', value: 2 },
            { key: 3, label: '付款方已收货，交易完成', value: 3 },
            { key: 4, label: '商家确认打款', value: 4 },
            { key: 5, label: '取消交易', value: 5 },
            { key: 6, label: '拒绝收货', value: 6 },
        ],
        goodsKindDict:[
            { key: 1, label: '虚拟商品', value: 1 },
            { key: 2, label: '实物商品', value: 2 }
        ],
        yesOrNo:[
            { key: 1, label: '收到付款', value: 1 },
            { key: 2, label: '未收到付款', value: null }
        ],
        sendYesOrNo:[
            { key: 1, label: '已发货', value: 0 },
            { key: 2, label: '未发货', value: 1 }
        ],
        defineYesOrNo:[
            { key: 1, label: '确认收货', value: 0 },
            { key: 2, label: '拒绝收货', value: 1 }
        ],
      // 选中数组
      ids: [],
        orderIds:'',
        orderList:[],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 交易单表格数据
      userList: null,
      // 弹出层标题
      title: "",
      // 部门树选项
      deptOptions: undefined,
      // 是否显示弹出层
      open: false,
      // 部门名称
      deptName: undefined,
      // 默认密码
      initPassword: undefined,
      // 日期范围
      dateRange: [],
      // 岗位选项
      postOptions: [],
      // 角色选项
      roleOptions: [],
      // 表单参数
      form: {},
      defaultProps: {
        children: "children",
        label: "label"
      },
      // 交易单导入参数
      upload: {
        // 是否显示弹出层（交易单导入）
        open: false,
        // 弹出层标题（交易单导入）
        title: "",
        // 是否禁用上传
        isUploading: false,
        // 是否更新已经存在的交易单数据
        updateSupport: 0,
        // 设置上传的请求头部
        headers: { Authorization: "Bearer " + getToken() },
        // 上传的地址
        url: process.env.VUE_APP_BASE_API + "/system/order/importData"
      },
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        recive: undefined,
        send: undefined,
        define: undefined,
        goodsKind: undefined,
        tradeCode: undefined,
        deptId: undefined
      },
      // 列信息
      columns: [
        { key: 0, label: `交易单id`, visible: true },
        { key: 1, label: `交易码`, visible: true },
        { key: 2, label: `用户编号`, visible: true },
        { key: 3, label: `商品类别`, visible: true },
        { key: 4, label: `商品名称`, visible: true },
        { key: 5, label: `收款金额`, visible: true },
        { key: 6, label: `担保费`, visible: true },
        { key: 7, label: `收款方支付金额`, visible: true },
        { key: 8, label: `付款方支付金额`, visible: true }
      ],
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" },
          { min: 2, max: 20, message: '用户名称长度必须介于 2 和 20 之间', trigger: 'blur' }
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" },
          { min: 5, max: 20, message: '用户密码长度必须介于 5 和 20 之间', trigger: 'blur' }
        ],
        email: [
          {
            type: "email",
            message: "请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    deptName(val) {
      this.$refs.tree.filter(val);
    }
  },
  created() {
    this.getList();
    // this.getDeptTree();
    this.getConfigKey("sys.user.initPassword").then(response => {
      this.initPassword = response.msg;
    });
    this.handleQuery()
  },
  methods: {
    /** 查询交易单列表 */
    getList() {
        // this.userList = []
      this.loading = true;
      list(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          // console.log(response,'res')
          this.userList = response.rows;
          this.total = response.total;
          // console.log(this.userList,'reslist')
          this.loading = false;
        }
      );
    },
    /** 查询部门下拉树结构 */
    // getDeptTree() {
    //   deptTreeSelect().then(response => {
    //     this.deptOptions = response.data;
    //   });
    // },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.label.indexOf(value) !== -1;
    },
    // 节点单击事件
    handleNodeClick(data) {
      this.queryParams.deptId = data.id;
      this.handleQuery();
    },
    // 交易单状态修改
    handleStatusChange(row) {
      let text = row.status === "0" ? "启用" : "停用";
        // console.log(row,text)
      this.$modal.confirm('确认要"' + text + '""' + row.goodsName + '"交易单吗？').then(function() {
          // console.log("123",row)
        return changeOrderStatus(row.orderId,row.status);
      }).then(() => {
        this.$modal.msgSuccess(text + "成功");
      }).catch(function() {
        row.status = row.status === "0" ? "1" : "0";
      });
    },
      // 收到付款修改
      handleStatusChangeReceive(row) {
        // if (row.status == '0'){
        //     this.isshow = true
        // }else {
        //     this.isshow = false
        // }
          console.log(row.status,'6678766788')
        // if (row.status == '0' ){
          let text = row.status === "1" ? "收到" : "未收到";
          this.$modal.confirm('确认商家"' + text + '""' + row.goodsName + '"货款吗？').then(function() {
              return changeOrderStatus(row.orderId,row.status);
          }).then(() => {
              this.$modal.msgSuccess(text + "成功");
          }).catch(function() {
              row.status = row.status === "0" ? "1" : "0";
          });
        // }
        // if ( row.status == '1' || row.status == '2'  ||  row.status == '3' || row.status == '4' ){
        //     alert('走过流程的交易单无法更改');
        //     return
        // }
      },
      // 商家确认打款修改
      handleStatusChange4(row) {
          // if (row.status == '0'){
          //     this.isshow = true
          // }else {
          //     this.isshow = false
          // }
          console.log(row.status,'6678766788')
          // if (row.status == '0' ){
          let text = row.status === "6" ? "确定" : "取消";
          this.$modal.confirm('付款方"' + text + '""' + row.goodsName + '"拒绝收货吗？').then(function() {
              return changeOrderStatus(row.orderId,row.status);
          }).then(() => {
              this.$modal.msgSuccess("商家"+text + "打款成功");
          }).catch(function() {
              row.status = row.status === "2" ? "6" : "2";
          });
          // }
          // if ( row.status == '1' || row.status == '2'  ||  row.status == '3' || row.status == '4' ){
          //     alert('走过流程的交易单无法更改');
          //     return
          // }
      },
      // 是否发货修改
      handleStatusChangeSend(row) {
          let text = row.send === "0" ? "已发货" : "未发货";
          this.$modal.confirm('确认"' + text + '""' + row.goodsName + '"交易单吗？').then(function() {
              return changeOrdersend(row.orderId,row.send);
          }).then(() => {
              this.$modal.msgSuccess(text + "成功");
          }).catch(function() {
              row.send = row.send === "0" ? "1" : "0";
          });
      },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
          orderId: undefined,
          userId: undefined,
          goodsName: undefined,
          goodsKind: undefined,
          tradeRule: undefined,
          tradeAddress: undefined,
          tradeMoney: undefined,
          tradeMoneyService: undefined,
          tradeMoneyMe: undefined,
          tradeMoneyYou: undefined,
          tradePassword: undefined,
          tradePasswordYou: undefined,
          tradeCode: undefined,
          tradeUrl: undefined,
          status: "0",
          send: "0",
          define: "0",
          recive: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.dateRange = [];
      this.queryParams.tradeCode  = undefined;
      this.queryParams.goodsKind = undefined;
      this.queryParams.status = null;
      // this.$refs.tree.setCurrentKey(null);
      this.handleQuery();
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId);
        this.orderIds = selection.map(item => item.orderId);
        this.orderList = selection.map(item => item.orderId);
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    // 更多操作触发
    // handleCommand(command, row) {
    //   switch (command) {
    //     case "handleResetPwd":
    //       this.handleResetPwd(row);
    //       break;
    //     case "handleAuthRole":
    //       this.handleAuthRole(row);
    //       break;
    //     default:
    //       break;
    //   }
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
        getOrder().then(response => {
        // this.postOptions = response.posts;
        // this.roleOptions = response.roles;
        this.open = true;
        this.title = "添加交易单";
        this.form.password = this.initPassword;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.orderIds;
        getListOne(row.orderId).then(response => {
        this.form = response.rows[0];
        // this.postOptions = response.posts;
        // this.roleOptions = response.roles;
        // this.$set(this.form, "postIds", response.postIds);
        // this.$set(this.form, "roleIds", response.roleIds);
        this.open = true;
        this.title = "修改交易单";
        // this.form.password = "";
      });
    },
      handleUpdate2(row) {
          this.reset();
          const orderId = row.orderId || this.orderIds;
          getListOne(orderId[0]).then(response => {
              this.form = response.rows[0];
              // this.postOptions = response.posts;
              // this.roleOptions = response.roles;
              // this.$set(this.form, "postIds", response.postIds);
              // this.$set(this.form, "roleIds", response.roleIds);
              this.open = true;
              this.title = "修改交易单";
              // this.form.password = "";
          });
      },
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        closeOnClickModal: false,
        inputPattern: /^.{5,20}$/,
        inputErrorMessage: "用户密码长度必须介于 5 和 20 之间"
      }).then(({ value }) => {
          resetUserPwd(row.userId, value).then(response => {
            this.$modal.msgSuccess("修改成功，新密码是：" + value);
          });
        }).catch(() => {});
    },
    /** 分配角色操作 */
    // handleAuthRole: function(row) {
    //   const userId = row.userId;
    //   this.$router.push("/system/user-auth/role/" + userId);
    // },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != undefined) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
              addOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.orderList;
      this.$modal.confirm('是否确认删除交易单编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/order/export', {
        ...this.queryParams
      }, `user_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮操作 */
    handleImport() {
      this.upload.title = "交易单导入";
      this.upload.open = true;
    },
    /** 下载模板操作 */
    importTemplate() {
      this.download('system/order/importTemplate', {
      }, `user_template_${new Date().getTime()}.xlsx`)
    },
    // 文件上传中处理
    handleFileUploadProgress(event, file, fileList) {
      this.upload.isUploading = true;
    },
    // 文件上传成功处理
    handleFileSuccess(response, file, fileList) {
      this.upload.open = false;
      this.upload.isUploading = false;
      this.$refs.upload.clearFiles();
      this.$alert("<div style='overflow: auto;overflow-x: hidden;max-height: 70vh;padding: 10px 20px 0;'>" + response.msg + "</div>", "导入结果", { dangerouslyUseHTMLString: true });
      this.getList();
    },
    // 提交上传文件
    submitFileForm() {
      this.$refs.upload.submit();
    }
  }
};
</script>
