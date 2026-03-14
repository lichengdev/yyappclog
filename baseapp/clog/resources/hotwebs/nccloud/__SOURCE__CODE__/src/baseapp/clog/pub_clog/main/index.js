/****************************************************************************************************
 * 源代码介绍：
 * 1,介绍
 *     此前端代码采用了单页应用写法, 即所有功能在一个页面中, 所以我们仅有一个前端文件(index.js) 这个前端文件表现节点
 * 的所有功能。大家可能会认为, 代码写在了一起是不是很臃肿,代码很多很乱很长，其实不然, 写在一起也是综合了各种因素权衡,
 * 主要权衡指标为 开发者前端基础, 改写代码习惯，和后端转前端的同学, 尽量避免this指针, 不用理解call调用, 能用一种整齐
 * 划一的方式，让大家理解简单，上手方便. 当你了解了代码的基本结构后, 你一定会觉得简单的不可思议。
 * 非常不建议在节点应用中使用现在的多页面模式方案。好处你一定会体会到.
 *
 * 2, 必备知识。
 * 2.1)react的运作模式.
 *    模型模型驱动视图改变, 视图被监听修改模型，再次驱动视图改变.  这种单向变化解决了节点所有功能。
 *    模型模型驱动视图改变 -> 可以理解为视图在表现模型,  在react中 state变化了, 会调用render方法重新渲染,即根据新模型
 *    重新画视图.
 *    视图被监听修改模型  -> 视图可以添加各种监听事件，(click, mourseMove)等, 事件处理时, 修改模型. 模型变化驱动了视
 *    图重新表现
 *         —————>>————————
 *        |               |
 *       模型(state)     视图(render)
 *        |               |
 *        -----<<----------
 * 2.2)平台的高阶组件
 *    平台的高阶组件是 props.组件.方法 这种调用形式的组件. 高阶组件最终底层还是 react运作模式.
 *    需要掌握高阶组件API. 平台的高阶组件会经常用到.
 *
 * 3,代码的基本结构,
 *    代码中, 最核心3方法，分别是构造方法constructor方法, 创建模型方法createState方法， 渲染方法render方法， 当然还是其
 * 他的定义，比如监听事件处理, 按钮状态控制方法，但是当你掌握了这3个最核心的方法, 你基本及掌握了整个页面的运作模式，结构模
 * 型了. 在介绍其他方法就一目了然了.
 *
 * 3.1 constructor
 *   constructor的作用为构造当前页面对象。主要职责为
 *   1) 加载NC资源，如单据模板, 按钮定义。 修正模板的一些属性。
 *   2) 调用创建模型方法createState方法，构建页面的结构(*), 核心方法。
 *   3) 保存和定义节点配置项，如节点编码，应用编码等。
 *
 * 3.2 createState方法，render方法
 *   createState方法为创建模型方法，了解createState对了解了解页面整体右至关重要的作用的.
 *   createState做了什么? createState在根据页面的组件布置情况创建对应的结构性的模型.并且
 *   模型的结构与页面的结构保持一致，这样非常方便了理解页面的整体情况，也非常变量的操作模型。
 *   我们举个简单例子[列表卡片]节点：
 *   我们先简单说明下这个节点， 这个节点包含了2个部分， 一个是列表的部分，一个是卡片的部分，
 *   并给他们起名字， 列表模式，卡片模式， 显示列表模式时，不显示卡片模式，显示卡片模式时，不显示列表模式，
 *   功能为列表卡片来回切换，列表模式中， 包含一个查询区， 一个列表区， 卡片模式中， 包含一个表单区。
 *   下面我们来构建一个state
 *    state = {
 *          showmode: 'list',   showmode表示为当前的显示模式， 是列表模式，还是卡片模式， 他可以有两个值 list,card分表表示
 *          list: {             列表模式的配置，包含查询区配置对象，表格区配置对象
 *             search:{         查询区配置
 *                  area: ''    查询区的区域编码,你在模板中定义的编码是什么他就是什么
 *                  onSearch:fn 点击查询时候的处理函数
 *             },
 *             table:{          表格区配置
 *                  area: ''    表格区的区域编码,你在模板中定义的编码是什么他就是什么
 *                  onSelect: fn 点击选中时候的处理函数
 *             }
 *          },
 *          card:{              卡片模式的配置，包含了表单区的配置对象
 *              form:{          表单区
 *                  area:''     表单区的区域编码，你在模板中定义的编码是什么他就是什么
 *                  onBeforeEdit:fn  表单编辑前的处理函数
 *              }
 *          }
 *    }
 *    以上我们就构建一个state,从这个state中，我们可以看到我们页面的模型全貌.
 *    下面我们来看render方法，
 *    render方法的中， 主要是渲染组件到页面，我们根据什么来渲染页面，根据上面构建的state模型来渲染，
 *    render = () =>{
 *          var renderList = () =>{ //渲染列表模式的是的页面，
 *          }
 *          var renderCard = () =>{ //渲染卡片模式的是的页面，
 *          }
 *    }
 *    我们应该怎么判断渲染的是列表 还是卡片呢， 我们通过state.showmode来确定，
 *    根据showmode的当前值的状态来判断是调用 renderList还是renderCard，
 *    这样当我们重新设置模型的值(setState),就能够借助React的机制(见react的运作模式)，驱动视图变化了(setState会驱动调用render)
 *    所以我们可以这样写
 *     render = () =>{
 *          var renderList = () =>{ //渲染列表模式的是的页面，
 *          }
 *          var renderCard = () =>{ //渲染卡片模式的是的页面，
 *          }
 *          return this.state.showmode == 'list' ? renderList() : renderCard();
 *    }
 *    是不是非常简单,当我们要在列表和卡片模式中切换时， 我们只需要 setState(showmode:'list'或者'card'),
 *    就可以在列表和卡片间来回切换了， 非常便利，再也不用什么缓存了，而且切换时也不需要加载什么模板.非常快捷。
 *
 *    那么renderList方法里面是怎么写的呢，也非常简单，我们再看一下state里面list的定义
 *    我们就可以根据list里面的定义写renderList了，state结构和render结构保持了一致
 *          list: {             列表模式的配置，包含查询区配置对象，表格区配置对象
 *             search:{         查询区配置
 *                  area: ''    查询区的区域编码,你在模板中定义的编码是什么他就是什么
 *                  onSearch:fn 点击查询时候的处理函数
 *             },
 *             table:{          表格区配置
 *                  area: ''    表格区的区域编码,你在模板中定义的编码是什么他就是什么
 *                  onSelect: fn 点击选中时候的处理函数
 *             }
 *          },
 *    renderList方法内部写法例子
 *           var renderList = () =>{ //渲染列表模式的是的页面，
 *              var {search, table } = this.state.list; //我们解构list里面的两个模型对象，就是search查询区的配置，table表格配置
 *                 return <div>
 *                  {this.props.search.NCCreateSearch(search.area, search)}
 *                   {this.props.table.createSimpleTable(table.area, table)}
 *                </div>
 *          }
 *    这样我们的列表界面就做完了，很简单 卡片模式也是和类似
 *    var renderCard = () =>{ //渲染卡片模式的是的页面，
 *           var { form } = this.card; //我们解构card里面的两个模型对象，就是search查询区的配置，table表格配置
 *          return <div>
 *                  {this.props.search.form(form.area, form)}
 *          </div>
 *    }
 *
 *    现在我们看一下完整的例子代码,并进行一些总结
 *    createState = () => {
 *      var state = {
 *          showmode: 'list',   showmode表示为当前的显示模式， 是列表模式，还是卡片模式， 他可以有两个值 list,card分表表示
 *          list: {             列表模式的配置，包含查询区配置对象，表格区配置对象
 *             search:{         查询区配置
 *                  area: ''    查询区的区域编码,你在模板中定义的编码是什么他就是什么
 *                  onSearch:fn 点击查询时候的处理函数
 *             },
 *             table:{          表格区配置
 *                  area: ''    表格区的区域编码,你在模板中定义的编码是什么他就是什么
 *                  onSelect: fn 点击选中时候的处理函数
 *             }
 *          },
 *          card:{              卡片模式的配置，包含了表单区的配置对象
 *              form:{          表单区
 *                  area:''     表单区的区域编码，你在模板中定义的编码是什么他就是什么
 *                  onBeforeEdit:fn  表单编辑前的处理函数
 *              }
 *          }
 *      }
 *    }
 *    render = () => {
 *          var renderList = () =>{
 *              var {search, table } = this.state.list; //我们解构list里面的两个模型对象，就是search查询区的配置，table表格配置
 *                 return <div>
 *                  {this.props.search.NCCreateSearch(search.area, search)}
 *                   {this.props.table.createSimpleTable(table.area, table)}
 *                </div>
 *          }
 *          var renderCard = () =>{
 *               var { form } = this.card; //我们解构card里面的两个模型对象，就是search查询区的配置，table表格配置
 *                return <div>
 *                  {this.props.search.form(form.area, form)}
 *                </div>
 *          }
 *          return this.state.showmode == 'list' ? renderList() : renderCard();
 *    }
 *    总结：
 *      代码state和render渲染的模型结构上是一致，这样我们能很快构建我们的页面，并非常容易修改，
 *      我们只需要修改我们的模型，就可以操控我们的页面了， 渲染只是在表现我们的模型
 ****************************************************************************************************/
import React, { Component } from 'react';
import ReactDOM from 'react-dom';

//=============导入高阶组件区=============
//1导入高阶组件,公共部分
import {createPage, base, ajax, high, toast, promptBox, deepClone} from 'nc-lightapp-front';
//2导入高阶组件, UIExtend的部分
import {Utils} from './Utils';
//3导入高阶组件, Plugin的部分
import {excelImportconfig} from 'nc-lightapp-front';  // 导入配置方法
const {ExcelImport: ExcelOutput} = high;  // 导出组件
import {print} from 'nc-lightapp-front';  // 打印组件
const {PrintOutput} = high;  // 输出组件
const {NCUploader} = high;  // 附件管理组件
//=============导入高阶组件区=============


//=============导入基础组件区=============
//1基础组件,公共部分
const {NCDiv, NCAffix, NCTabs, NCScrollElement, NCTooltip} = base;
//2导入基础组件. UIExtend部分

//3导入基础组件, Plugin的部分
//=============导入基础组件区=============


//=============基本变量定义区=============
//1 基础常用变量声明部分
const EMPTY_FN = function(){}; //空函数

const URLS = {  // 请求路径
    printUrl: '/fbip/baseapp/clog/PrintClogVOAction.do',
    saveUrl: '/fbip/baseapp/clog/SaveClogVOAction.do',
    deleteUrl: '/fbip/baseapp/clog/DeleteClogVOAction.do',
    listUrl: '/fbip/baseapp/clog/ListClogVOAction.do',
    loadUrl: '/fbip/baseapp/clog/LoadClogVOAction.do',
    addUrl: '/fbip/baseapp/clog/AddClogVOAction.do',
    copyUrl: '/fbip/baseapp/clog/CopyClogVOAction.do'
};

const ACTIONS = {  // 按钮编码
    ADD: 'Add',  //新增
    DELETE: 'Delete',  //删除
    EDIT: 'Edit',  //修改
    REFRESH: 'Refresh',  //刷新
    QUERY: 'Query',  //查询
    CANCEL: 'Cancel',  //取消
    SAVE: 'Save',  //保存
    SAVEADD: 'SaveAdd',  // 保存新增
    COPY: 'Copy',  // 复制
    IMPORT: 'Import',  //导入
    EXPORT: 'Export',  //导出
    PRINT: 'Print',  //打印
    OUTPUT: 'Output',  //输出
    ATTACHMENT: 'Attachment',  //附件
}

const FIELDS = {  // 字段编码
    PRIMARYKEY: 'pk_log'
}

//2 编辑模式变量
const EDITMODE_EDIT = 'edit';
const EDITMODE_BROWSE = 'browse';
const EDITMODE_ADD = 'add';

//3 页面显示模式
//3.1 当前UIExtend需要定义的状态
const SHOWMODE_LIST = 'list';
const SHOWMODE_CARD = 'card';


const EXCELBILLTYPE = 'PUB_CLOG_MKH30101';  // 导入导出单据类型

const PRINTNODEKEY_LIST = 'MKH3010101';  // 列表打印模板标识
const PRINTNODEKEY_CARD = 'MKH3010102';  // 卡片打印模板标识

//=============基本变量定义区=============

/****************************************************************************************************
 * 整体介绍：
 *      目前的这种写法是 单页应用的写法，多页面应用，页面切换时，采用的是“安装-卸载”的方式体现在render方法
 *      目前的这种写法，我们不需要再关注this指针的问题,也不需要在调用方法时使用call来切换指针并执行，直接"方法()"即可
 *      目前的这种写法，采用的是MVVM的架构模式，把state看做是VM，所以我们需要把目光集中在state
 ****************************************************************************************************/

export class ApplicationPage extends Component{

    /**
     * 构造方法,js要求的必须方法,构造整个页面对象，
     * 此方法构造时，会定义全局配置，this.config,方便其他方法取用,
     * 同时，也处理了加载模板,按钮，设置模板按钮功能，最终通过调用
     * pageReady(此方法可以加入你的业务逻辑)完成第一次页面.
     * 此方法中,会调用initButton,initMeta来初始化模板和按钮,
     * initButton,initMeta,pageReady仅在页面打开时调用一次.
     * 其他时候不再调用
     */
    constructor(props) {
        super(props); //构造
        /*
         * 节点全局变量定义
         * 包含 页面编码定义,应用编码定义,标题定义,模块组件名定义
         * 配置的获取方式，优先取平台定义,其次取传入配置定义, 最后默认
         */
        this.config = {
            pagecode: props.getSearchParam('p')   || props.appcode || 'pagecode未定义', //页面编码定义
            appcode: props.getSearchParam('c')    || props.appcode || 'appcode未定义',  //应用编码定义
            title: this.props.getSearchParam('n') || props.title   || 'Demo主从表',     //表体定义
            moduleId: props.appcode,  //多语资源目录id
            domainName: 'baseapp' //模块域名
        }

        /**
         * 创建state,并构造节点模型,其结构与渲染时是一致的.
         * 可参照顶端代码注释的详细解释
         */
        this.state = this.createState();


        /**
         * 加载NCC资源,
         * 1,包含单据模板,按钮,等平台定义的功能
         * 2,加载多语资源文件,
         * 3,加载需要在代码总用到参照js
         */
        Utils.loadNCCResource({
            props: props,
            referObjs: [],
            /**
             * 导入全局配置,加载资源时 appcode,pagecode,modulename等信息,
             * 需要用到,全局配置中包含所有它需要用到的参数,
             */
            ...this.config,
            //导入全局配置,
            callback: (data) => {
                let {context, template, button, lang, refer = {}} = data;
                /**
                 * 初始化模板,修改模板相关配置
                 * 初始化按钮,修改按钮相关配置,
                 * 并将模板配置,按钮配置输入到平台,通过setMeta,setButtons输入.
                 * 让平台进行初始化.当平台初始化完成后,通过then继续后续的工作
                 */
                template = this.initMeta(template); //更新模板
                button = this.initButton(button);
                Promise.all([//合并处理 模板 和 多语
                    new Promise(resolve => this.props.meta.setMeta(template, () => resolve(true))),
                    new Promise(
                        resolve => this.props.button.setButtons(button, () => resolve(true)))
                ]).then(() => {
                    this.setState({isPageReady: true}, () => {  //标记自己页面已经完成,并进行第一次的渲染
                        let {moduleName, billType} = this.state.execlExport; //此处简化解构,一遍能够代码清晰
                        this.props.button.setUploadConfig('Import',
                            excelImportconfig(this.props, moduleName, billType, true, '', this.config, () => { // 导入后回调
                                let {showmode} = this.state;
                                if (showmode != SHOWMODE_LIST) { // 不是列表态导入不进行刷新
                                    return;
                                }
                                // 调用列表查询方法，通过回调处理查询结果
                                this.queryListData({
                                    callback: (data) => { // data为查询结果
                                        // 执行列表查询后操作
                                        this.afterLoadList({data});
                                    }
                                });
                            }));
                        (this.pageReady || EMPTY_FN)();
                    });
                }).catch((e) => {
                    throw new ReferenceError(e);
                });
            }
        });
    }

    /**
     * pageReady方法为页面已经完全设置完毕，
     * 可以初始化一些初始的功能，比如查询下列表并显示数据等
     */
    pageReady = () => {
        this.updateBtnStatus(); // 更新按钮状态
    }

    /**
      * 初始化平台定义的单据模板
      * 触发时机：执行loadNCCResource,加载完模板、多语、等初始化信息之后触发
      * 功能：对加载完的模板进行个性化调整
      * 可实现举例功能:
      * 1.参照表单联动过滤, 参见[Demo1] 
      * 2.处理表格操作列,   参见[Demo2]
      */
    initMeta = (meta) => {
        // 添加列表行操作列
        meta['pub_clog_list'].items.push({
            attrcode: 'opr', // 列标识, 固定
            itemtype:'customer', // 列类型, 固定
            label: '操作', // 列名
            width: 200, // 列宽度
            className : 'table-opr', // 样式
            fixed: 'right', // 悬浮方向
            visible: true, // 是否可见
            render: (text, record, index) => { // 渲染方法
                //获得符合条件按钮的key（编码）的集合
                let oprButtons = [ACTIONS.EDIT, ACTIONS.DELETE];
                // 创建操作列API
                return this.props.button.createOprationButton(oprButtons, {
                    area: 'common', // 按钮区域编码
                    buttonLimit: 3, // 允许的按钮最多显示数量
                    onButtonClick: (props, btncode) => this.onBtnClickHead(props, btncode, {record, index}) // 点击按钮
                });
            }
        });
    	/**
         * 主组织权限过滤 : 加载的是当前登录人有权限的组织
         */
        // 卡片表单区主组织字段添加权限过滤
    	meta['pub_clog_form'].items.forEach(item => {
            if (item.attrcode == "pk_org") {
                item.queryCondition = () => {
                    return {
                        AppCode : this.config.appcode,
                        TreeRefActionExt : 'nccloud.web.refer.sqlbuilder.PrimaryOrgSQLBuilder'
                    };
                };
            }
        });
    	// 列表查询区主组织字段添加权限过滤
    	meta['pub_clog_query'].items.forEach(item => {
            if (item.attrcode == "pk_org") {
                item.queryCondition = () => {
                    return {
                        AppCode : this.config.appcode,
                        TreeRefActionExt : 'nccloud.web.refer.sqlbuilder.PrimaryOrgSQLBuilder'
                    };
                };
            }
        });

        // 云原生适配，saga开头的属性全部隐藏
        meta['pub_clog_form'].items.forEach(item => {
            if (item.attrcode.startsWith('saga')) {
                item.visible = false;
                item.disabled = true;
            }
        });
        meta['pub_clog_list'].items.forEach(item => {
            if (item.attrcode.startsWith('saga')) {
                item.visible = false;
                item.disabled = true;
            }
        });
        /**
         * Demo[1]查询区参照过滤
         * 场景：查询区上同时有部门、组织、集团字段，部门受组织和集团过滤
         * 效果：部门选择时，通过选择的组织和选择的集团进行部门参照过滤
         */
        // meta['ScrapApplyVO_query'].items.find(item => item.attrcode == 'pk_dept').queryCondition = () => { // 部门设置queryCondition属性
        //     let pk_group = this.props.search.getSearchValByField('ScrapApplyVO_query', 'pk_group'); // 获取查询区集团字段值
        //     let pk_org = this.props.search.getSearchValByField('ScrapApplyVO_query', 'pk_org'); // 获取查询区组织字段值
        //     return { // 组装参数返回
        //         pk_group: pk_group && pk_group.value && pk_group.value.firstvalue || '', // 集团主键
        //         pk_org: pk_org && pk_org.value && pk_org.value.firstvalue || '', // 组织主键
        //         TreeRefActionExt: 'nccloud.web.lcdp.lcdpscrapapply.scrapapplyvo.action.ref.DeptTreeRefCustomFilter' // 自定义过滤
        //     };
        // };

        /**
         * Demo[2] 表单区参照过滤
         * 场景：表单上同时有部门、组织、集团字段，部门受组织和集团过滤
         * 效果：部门选择时，通过选择的组织和选择的集团进行部门参照过滤
         */
        // meta['ScrapApplyVO_form'].items.find(item => item.attrcode == 'pk_dept').queryCondition = () => { // 部门设置queryCondition属性
        //     let pk_group = this.props.form.getFormItemsValue('ScrapApplyVO_form', 'pk_group'); // 获取查询区集团字段值
        //     let pk_org = this.props.form.getFormItemsValue('ScrapApplyVO_form', 'pk_org'); // 获取查询区组织字段值
        //     return { // 组装参数返回
        //         pk_group: pk_group && pk_group.value || '', // 集团主键
        //         pk_org: pk_org && pk_org.value || '' // 组织主键
        //     };
        // };

        /**
         * Demo[3] NCTooltip提示
         * 效果： 鼠标移动到部门字段上显示气泡提示
         */
        // let overlay = (<span><p>{'输入长度小于20'}</p><p>{'1 XXXX'}</p><p>{'2 XXXX'}</p></span>); // 气泡提示内容
        // let pk_dept = meta['ScrapApplyVO_form'].items.find(item => item.attrcode == 'pk_dept'); // 获取部门模板字段
        // meta['ScrapApplyVO_form'].items.find(item => item.attrcode == 'pk_dept').label = ( // 修改模板字段标签
        //     <NCTooltip overlay={overlay} placement={'top'} trigger={'hover'} inverse>
        //         <div title={''}>{pk_dept.label}</div>
        //     </NCTooltip>
        // );
        return meta;
    }

    /**
     * initButton方法 功能介绍：
     * 触发时机：执行loadNCCResource,加载完模板、多语、等初始化信息之后触发
     * 功能简介：对加载完的按钮数据进行个性化调整
     * 举例功能：
     * 1、禁用某按钮 参见Demo1
     */
    initButton = (button) => {
        /**
         * Demo1 处理按钮
         * 场景描述： 有时需要在按钮数据加载之后进行一些处理
         * 达成效果： 按钮不可见，并且禁用
         * 写法解释： 迭代所有按钮，找到Save按钮，设置按钮的可见性和禁用型
         * 代码示例:
         */
        //buttons.find(btn=>{//迭代buttons
        //  if(btn.key == 'Save'){//找到button的key为Save的按钮
        //      btn.disabled = true;//设置按钮禁用
        //      btn.visible = false;//设置按钮隐藏
        //  }
        //})
        return button;
    }

    /**
     * 创建state模型
     * state的模型结构于页面结构是一致的.请优先阅读开头的说明 3.2 createState方法，render方法
     * state中得必有并且常用得属性
     * isPageReady: 页面是否可以进行渲染，构造函数会异步请求模板,所以构造函数执行完成后,
     *              React在构造函数执行完后会立即调用render方法渲染页面,此时可能模板还没有加载完成,
     *              所以通过此属来标记模板等初始化数据是否加载完成. 加载完成后,isPageReady=true.
     *              才开始正式得渲染页面，参考render方法这种对isPageReady的使用。
     * showmode：   页面当前的显示模型,SHOWMODE为前缀的常量定义,你的应用有几个页面的,就有几个SHOWMODE的常量定义
     *              分别对应不同页面,比如，主从页面中(不带插件), 有列表和卡片两个页面, 则会使用到SHOWMODE_LIST
     *              SHOWMODE_CARD, render方法的中根据showmode值,来具体渲染页面.
     * editmode:    页面当前编辑模式,有两种状态, EDITMODE为前传的常量定义， EDITMODE_BROWSE,EDITMODE_EDIT;
     *              高阶组件的中的编辑状态与它保持一致的。当设置或改变editmode时,高阶组件的状态也要随之变化,
     *              如form formlist.
     * 模型结构定义说明： 建议优先阅读开头的说明3.2 createState方法，render方法
     * 这块基本模型，含义，表示是什么，起什么作用：参考3.2 create方法， 但是要结合你的模型
     * 主子表
     *	head: {  // 节点标题栏
     *		title: this.config.title, // 标题
     *		initShowBackBtn: false, // 是否显示返回按钮
     *		backBtnClick: this.onBackCard // 点击返回按钮
     *	},
     *	headBtn: { // 头部按钮区
     *		area: 'common', // 区域编码
     *		onButtonClick: this.onBtnClickHead // 点击按钮
     *	},
     *	list: { // 列表页面
     *		search: { // 查询区
     *			area: 'MasterVO_query', // 区域编码
     *			clickSearchBtn: this.onSearchList // 点击查询按钮
     *		},
     *		table: { // 主表表格
     *			allpks: [], // 列表查询结果行pk数组
     *			area: 'MasterVO_list', // 区域编码
     *			showCheck: true, // 是否显示多选框
     *			showIndex: true, // 是否显示行序号
     *			adaptionHeight: false, // 是否自适应浏览器高度
     *			onRowClick: this.onRowClickTable, // 点击行
     *			onRowDoubleClick: this.onRowDoubleClickTable, // 双击行
     *			handlePageInfoChange: (props, config, pks, total) => { // 表格分页信息改变及点击翻页按钮
     *				this.queryListData({ // 查询列表
     *					callback: data => this.afterLoadList({data}) // 执行列表查询后逻辑
     *				});
     *			},
     *			selectedChange: this.onListSelectRowsChanged, // 选择框的选中行改变
     *			onSelectedAll: this.onListSelectRowsChanged, // 点击全选框
     *			onSelected: this.onListSelectRowsChanged // 点击选择框
     *		},
     *		tablelist: { // 列表子表详情
     *			tablelistRow: {}, // 列表子表明细对应主表行数据，只有当点击主表表格行时才会给这个属性设值，重新查询列表会清空 格式：{values: {}}
     *			activeKey: 'ChildVO_list', // 当前显示页签
     *			onChange: this.onTabChangedTablelist, // 选中页签改变
     *			onTabClick: EMPTY_FN, // 点击页签
     *			tablePanes: { // 页签信息
     *				'ChildVO_list'//区域编码//: {
     *					key: 'ChildVO_list', // 页签唯一标识
     *					tab: '子实体', // 页签显示名称
     *					table: { // 子表表格
     *						area: 'ChildVO_list', // 区域编码
     *						showIndex: true, // 是否显示行序号
     *						showCheck: false, // 是否显示多选框
     *						adaptionHeight: true, // 是否自适应浏览器高度
     *						handlePageInfoChange: (props, config, pks, total) => { // 表格分页信息改变及点击翻页按钮
     *							let {tablelistRow} = this.state.list.tablelist; // 获取子表详情对应主表行数据
     *							if (!(tablelistRow && tablelistRow.values)) { // 不存在对于主表行数据
     *								return;
     *							}
     *							this.querySlaveData({ // 查询子表
     *								callback: data => this.fillSlaveData({data}) // 填充数据
     *							});
     *						}
     *					}
     *				},
     *			}
     *		}
     *	},
     *	card: { // 卡片页面
     *		form: { // 表单
     *			area: 'MasterVO_form', // 区域编码
     *			onBeforeEvent: this.onBeforeEditForm, // 编辑前事件
     *			onAfterEvent: this.onAfterEditForm, // 编辑后事件
     *		},
     *		formlist: { // 子表表格
     *			'ChildVO_sub'//区域编码//: {
     *				area: 'ChildVO_sub', // 区域编码
     *				showIndex: true, // 是否显示行序号
     *				showCheck: true, // 是否显示多选框
     *				adaptionHeight: true, // 是否自适应浏览器高度
     *				onAfterEvent: this.onAfterEditFormlist, // 编辑后事件
     *				onBeforeEvent: this.onBeforeEditFormlist, // 编辑前事件
     *				onSelected: this.onSelectedChangeFormlist, // 点击选择框
     *				onSelectedAll: this.onSelectedChangeFormlist, // 点击全选框
     *				selectedChange: this.onSelectedChangeFormlist, // 选择框的选中行改变
     *				tableHead: () => this.state.card.formlist['ChildVO_sub'].button.render(), // 肩部渲染，主要用于渲染肩部操作按钮，调用肩部按钮的渲染方法
     *				button: { // 肩部按钮
     *					area: 'ChildVO', // 区域编码
     *					onButtonClick: (props, btnCode) => this.onBtnClickFormlist(props, btnCode, 'ChildVO_sub'), // 点击按钮
     *					render: () => { // 渲染方法
     *						return (
     *							<div className="shoulder-definition-area">
     *								<div className="btn-group">
     *									{//按钮//}
     *									{this.props.button.createButtonApp(this.state.card.formlist['ChildVO_sub'].button)}
     *								</div>
     *							</div>
     *						);
     *					}
     *				}
     *			}
     *		},
     *		cardPagniation: { // 翻页组件
     *			visible: false, // 是否显示
     *			handlePageInfoChange: this.onBtnClickCardPagniation // 点击翻页器按钮
     *		}
     *	}
     *
     * 其他功能是如何在state中定义
     * 特性模型的加入通过，Utils.apply方法蒋其他模型(插件模型加入到state中)
     * 可参见此方法中的具体的Utils.apply的注释
     * 例子：
     * state = { //基本模型
     *      list:{} 
     *      card:{}
     * }
     * uploader: { //上传组件
     *      visible: false, 
     *      billId: '',
     *      onHide: this.onHideUploader
     * }
     * 通过调用Utils.apply方法 Utils.apply(state, uploader);
     * 体现的结果为:
     * state = { //基本模型
     *      list:{} 
     *      card:{}
     *      uploader: { //上传组件
     *          visible: false, 
     *          billId: '',
     *          onHide: this.onHideUploader
     *      }
     * }
     */
    createState = () => {
        let state = {
            isPageReady: false, // 页面是否可以渲染
            showmode: SHOWMODE_LIST, // 页面当前的显示模型
            editmode: EDITMODE_BROWSE, // 页面当前编辑模式
            head: {  // 节点标题栏
                title: this.config.title, // 标题
                affixed: false,
                initShowBackBtn: false, // 是否显示返回按钮
                backBtnClick: this.onBackCard // 点击返回按钮
            },
            headBtn: { // 头部按钮区
                area: 'common', // 区域编码
                onButtonClick: this.onBtnClickHead // 点击按钮
            },
            list: { // 列表页面
                search: { // 查询区
                    area: 'pub_clog_query', // 区域编码
                    clickSearchBtn: this.onSearchList // 点击查询按钮
                },
                table: { // 主表表格
                    allpks: [], // 列表查询结果行pk数组
                    area: 'pub_clog_list', // 区域编码
                    showCheck: true, // 是否显示多选框
                    showIndex: true, // 是否显示行序号
                    adaptionHeight: true, // 是否自适应浏览器高度
                    onRowClick: this.onRowClickTable, // 点击行
                    onRowDoubleClick: this.onRowDoubleClickTable, // 双击行
                    handlePageInfoChange: (props, config, pks, total) => { // 表格分页信息改变及点击翻页按钮
                        // 调用列表查询方法，通过回调处理查询结果
                        this.queryListData({
                            callback: data => this.afterLoadList({data}) // 执行列表查询后逻辑，data为查询结果
                        });
                    },
                    selectedChange: this.onListSelectRowsChanged, // 选择框的选中行改变
                    onSelectedAll: this.onListSelectRowsChanged, // 点击全选框
                    onSelected: this.onListSelectRowsChanged // 点击选择框
                }            },
            card: { // 卡片页面
                form: { // 表单
                    area: 'pub_clog_form', // 区域编码
                    onBeforeEvent: this.onBeforeEditForm, // 编辑前事件
                    onAfterEvent: this.onAfterEditForm, // 编辑后事件
                },
                cardPagniation: { // 翻页组件
                    visible: false, // 是否显示
                    dataSource: this.config.pagecode,
                    handlePageInfoChange: this.onBtnClickCardPagniation // 点击翻页器按钮
                }
            }
        };
        // 导入导出
        Utils.apply(state, {
            execlExport: { // 导入导出配置
                moduleName: this.props.domainName, //模块名
                billType: EXCELBILLTYPE, // 导出配置标识
                selectedPKS: [], // 选中行pk
                appcode: this.config.appcode, //appcode
                pagecode: this.config.pagecode, //pagecode
            }
        });
        // 打印输出
        Utils.apply(state, {
            printOutput: { // 输出打印配置
                ref: 'printOutput', // 添加引用到当前组件，就可以通过this.refs.printOutput调用组件的方法
                url: URLS.printUrl, // 打印输出Url
                data: {
                    funcode: this.config.appcode, // appcode
                    oids: [], // 选中行id
                    nodekey: PRINTNODEKEY_LIST, // 打印模板标识
                    outputType: 'output' // 打印输出方式
                }
            }
        });
        // 附件管理
        Utils.apply(state, {
            uploader: { // 附件管理配置
                visible: false, // 是否可见
                billId: undefined, // 选中单据id
                onHide: this.onHideUploader, // 关闭附件管理回调
            }
        });

        // Utils.apply(state, {
        //     demoModal: {
        //         title: 'Demo弹窗',// 弹框表头信息
        //         content: '这是一个Demo弹窗', //弹框内容，可以是字符串或dom
        //         closeModalEve: this.closeModalEve, //关闭按钮事件回调
        //         size: 'lg', //  模态框大小 sm/lg/xlg/xxlg/max 【className为junior/senior/combine和size不要同时配置】
        //         noFooter : true, //是否需要底部按钮,默认有footer,有false,没有true
        //     }
        // });

        return state;
    }

    //4 渲染方法
    render() {
        let {isPageReady, showmode, head, headBtn, list, card} = this.state;

        if(!isPageReady) return ''; //页面资源加载完成后才能渲染页面

        // 渲染列表
        const renderList = () => {
            let {search, table} = list;
            if (showmode != SHOWMODE_LIST) {
                return '';
            }
            return (
                <div className="nc-bill-list">
                    <NCAffix onChange={({affixed, event}) => {this.state.head.affixed = affixed; this.setState(this.state)}}>
                        <NCDiv areaCode={NCDiv.config.HEADER} className="nc-bill-header-area" style={this.state.head.affixed && {backgroundColor: '#e0f2ff'}}>
                            <NCDiv areaCode={NCDiv.config.Title} className='header-title-search-area'>
                                {this.props.BillHeadInfo.createBillHeadInfo(head)}
                            </NCDiv>
                            <div className="header-button-area">
                                {this.props.button.createButtonApp(headBtn)}
                            </div>
                        </NCDiv>
                    </NCAffix>
                    <NCDiv areaCode={NCDiv.config.SEARCH} className="nc-bill-search-area">
                        {this.props.search.NCCreateSearch(search.area, search)}
                    </NCDiv>
                    <NCDiv areaCode={NCDiv.config.List} className="nc-bill-table-area" >
                        {this.props.table.createSimpleTable(table.area, table)}
                    </NCDiv>
                </div>
            );
        };

        // 渲染卡片
        const renderCard = () => {
            let {form, cardPagniation} = card;
            if (showmode != SHOWMODE_CARD) {
                return '';
            }
            return (
                <div className='nc-bill-extCard'>
                    <div className="nc-bill-top-area" style={{height: '100%'}}>
                        <NCAffix>
                            <NCDiv areaCode={NCDiv.config.HEADER} className='nc-bill-header-area'>
                                <NCDiv areaCode={NCDiv.config.Title} className='header-title-search-area'>
                                    {this.props.BillHeadInfo.createBillHeadInfo(head)}
                                </NCDiv>
                                <div className="header-button-area">
                                    {this.props.button.createButtonApp(headBtn)}
                                </div>
                                <NCDiv areaCode={NCDiv.config.TABS} className='header-cardPagination-area' style={cardPagniation.visible ? {} : {display: 'none'}}>
                                    {this.props.cardPagination.createCardPagination(cardPagniation)}
                                </NCDiv>
                            </NCDiv>
                        </NCAffix>
                        <NCDiv areaCode={NCDiv.config.FORM} className="nc-bill-form-area">
                            <NCScrollElement name={form.area}>
                                {this.props.form.createForm(form.area, form)}
                            </NCScrollElement>
                        </NCDiv>
                    </div>
                </div>
            );
        };

        // 导入导出组件
        const renderExcelOutput = () => {
            let {execlExport} = this.state;
            if (showmode != SHOWMODE_LIST && showmode != SHOWMODE_CARD) {
                return '';
            }
            return (
                <ExcelOutput
                    {...Object.assign(this.props)}
                    {...execlExport}
                />
            );
        }

        // 打印输出组件
        const renderPrintOutput = () => {
            let {printOutput} = this.state;
            if (showmode != SHOWMODE_LIST && showmode != SHOWMODE_CARD) {
                return '';
            }
            return (
                <PrintOutput {...printOutput}/>
            );
        }

        // 附件组件
        const renderUploader = () => {
            let {uploader} = this.state;
            if ((showmode != SHOWMODE_LIST && showmode != SHOWMODE_CARD) || !uploader.visible) {
                return '';
            }
            return (
                <NCUploader {...uploader}/>
            );
        }

        return (
            <div>
                {renderList()}
                {renderCard()}
                {renderExcelOutput()}
                {renderPrintOutput()}
                {renderUploader()}
                {/*{this.props.modal.createModal('demoModal', this.state.demoModal)}*/}
            </div>
        );
    }

    //=============功能性方法区=============

    // 修改按钮状态
    // callback  回调，无参数
    updateBtnStatus = ({callback = EMPTY_FN} = {}) => {
        let {showmode, editmode, list: {table: {allpks}}} = this.state;
        let isBrowse = editmode == EDITMODE_BROWSE;
        let checkedRows = this.getCheckedRows();
        // 按钮可用性初始化
        let hasCheckedRow = checkedRows && checkedRows.length > 0;
        let isCheckedOne = hasCheckedRow && checkedRows.length == 1;

        // 按钮可见性初始化
        let btnVisible = {
            [ACTIONS.ADD]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse,
            [ACTIONS.EDIT]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse,
            [ACTIONS.DELETE]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse,
            [ACTIONS.REFRESH]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse,
            [ACTIONS.SAVE]: showmode == SHOWMODE_CARD && !isBrowse,
            [ACTIONS.SAVEADD]: showmode == SHOWMODE_CARD && !isBrowse && !hasCheckedRow,
            [ACTIONS.CANCEL]: showmode == SHOWMODE_CARD && !isBrowse,
            [ACTIONS.COPY]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse
            // ,'ShowModal': (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse
        }

        let btnDisabled = {
            [ACTIONS.ADD]: false,
            [ACTIONS.EDIT]: !hasCheckedRow,
            [ACTIONS.DELETE]: !hasCheckedRow,
            [ACTIONS.REFRESH]: showmode == SHOWMODE_LIST ? !(allpks && allpks.length > 0)
                : (showmode == SHOWMODE_CARD ? !hasCheckedRow : false),
            [ACTIONS.SAVE]: false,
            [ACTIONS.SAVEADD]: false,
            [ACTIONS.CANCEL]: false,
            [ACTIONS.COPY]: !hasCheckedRow
            // ,'ShowModal': false
        }


		// 导入导出按钮控制
		Object.assign(btnVisible, {
			[ACTIONS.IMPORT]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse,
			[ACTIONS.EXPORT]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse
		});
		Object.assign(btnDisabled, {
			[ACTIONS.IMPORT]: false,
			[ACTIONS.EXPORT]: false
		});
		// 打印输出按钮控制
		Object.assign(btnVisible, {
			[ACTIONS.PRINT]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse,
			[ACTIONS.OUTPUT]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse
		});
		Object.assign(btnDisabled, {
            [ACTIONS.PRINT]: !hasCheckedRow,
            [ACTIONS.OUTPUT]: !hasCheckedRow
		});
		// 附件按钮控制
		Object.assign(btnVisible, {
			[ACTIONS.ATTACHMENT]: (showmode == SHOWMODE_LIST || showmode == SHOWMODE_CARD) && isBrowse
		});
		Object.assign(btnDisabled, {
            [ACTIONS.ATTACHMENT]: !isCheckedOne
		});

        this.props.button.setButtonVisible(btnVisible);
        this.props.button.setButtonDisabled(btnDisabled);
		// 设置卡片翻页器是否可见
        this.state.card.cardPagniation.visible = showmode == SHOWMODE_CARD && editmode == EDITMODE_BROWSE;
        this.setState(this.state, callback);
    }

    // 查询列表数据
    // callback  回调， (data: 查询结果，格式参考后端Grid类) => {}
    queryListData = ({callback = EMPTY_FN} = {}) => {
        let {list: {search, table}} = this.state;
        // 获取查询区参数
        let queryInfo = this.props.search.getQueryInfo(search.area);
        // 获取表格分页信息
        let pageInfo = this.props.table.getTablePageInfo(table.area);
        let param = { // 组装参数
            appcode: this.config.appcode, // appcode
            pageCode: this.config.pagecode, // pagecode
            formId: table.area, // 表格区域编码
            queryTreeFormatVO: {...queryInfo, pageCode: this.config.pagecode}, // 查询条件
            pageInfo // 分页信息
        };
        // 判断是否获取到查询条件，当查询区有必填项未填写时获取查询区参数oid会是空
        if (!param.queryTreeFormatVO.oid) {
            return;
        }
        ajax({
            url: URLS.listUrl,
            data: param,
            success: (res) => {
                let {data: {data} = {}} = res;
                callback(data);
            },
        });
    }

    // 填充列表数据
    // data  列表数据，格式参考后端Grid类，为空则会清空
    // callback  回调，无参数
    fillListData = ({data, callback = EMPTY_FN} = {}) => {
        let {list: {table}} = this.state;
        // 从data获取表格区域数据
        let tableData = data && data[table.area] ? data[table.area] : {rows: []};
        // 设置数据到表格组件
        this.props.table.setAllTableData(table.area, tableData);
        setTimeout(callback);
    }

    // 查询卡片数据
    // data:  查询参数
    //      pk  主键
    //      pageCode  pagecode
    //      formId  表单区域编码
    //      tableId  子表表格区域编码数组
    // callback  回调，(data: 查询结果，格式参考后端ExtBillCard类) => {}
    queryCardData = ({data, callback = EMPTY_FN} = {}) => {
        // pk pageCode formId 不能为空
        if (!(data && data.pk && data.pageCode && data.formId)) {
            return;
        }
        // 查询请求
        ajax({
            url: URLS.loadUrl,
            data: data,
            success: (res) => {
                let {data: {data} = {}} = res;
                callback(data);
            },
        });
    }

    // 填充卡片数据
    // data  卡片数据，格式参考后端ExtBillCard类
    // callback  回调，无参数
    fillCardData = ({data, callback = EMPTY_FN} = {}) => {
        // 获取卡片区域编码
        let area = this.getCardAreaCode();
        // 设置主表单数据
        this.props.form.setFormItemsValue(area.formId, data && data.head && data.head[area.formId] && data.head[area.formId].rows[0].values || {});
        setTimeout(callback);
    }

    // 清空卡片数据
    // callback  回调，无参数
    clearCardData = ({callback = EMPTY_FN} = {}) => {
        // 获取卡片区域编码
        let area = this.getCardAreaCode();
        // 清空主表单数据
        this.props.form.EmptyAllFormValue(area.formId);
        setTimeout(callback);
    }

    // 获取卡片数据，格式参考后端ExtBillCard类
    getCardData = () => {
        // 获取卡片区域编码
        let area = this.getCardAreaCode();
        // 获取卡片界面数据（主表单、子表表格）
        let cardData = this.props.createExtCardData(this.config.pagecode, area.formId, []);
        return cardData;
    }

    // 获取卡片区域编码，包括主表单（formId）子表格（tableId），单表时没有tableId
    // 返回格式：{formId: '', tableId: ['']}
    getCardAreaCode = () => {
        return {
            formId: this.state.card.form.area
        }
    }



    //=============操作流程方法区=============

    /**
     * 方法功能：
     *      根据当前页面显示模式获取选中行
     * @returns {*[]|*} 返回选中行数组[{values:{itemcode:{value:'', display:''}}}, {values:{itemcode:{value:'', display:''}}}]
     */
    getCheckedRows = () => {
        let {showmode, list: {table}, card: {form}} = this.state;
        if (showmode == SHOWMODE_LIST) { // 当前显示为列表页面时
            // 获取列表表格选中行
            let checkedRows = this.props.table.getCheckedRows(table.area);
            // 结构为固定格式返回，格式：[{values: {}}]
            return checkedRows ? checkedRows.map(row => row.data) : [];
        } else if (showmode == SHOWMODE_CARD) { // 当前显示为卡片页面时
            // 获取卡片主表单数据
            let checkedRow = this.props.form.getAllFormValue(form.area);
            // 结构为固定格式返回，主键必须有值，格式：[{values: {}}]
            return checkedRow && checkedRow.rows && checkedRow.rows[0].values[FIELDS.PRIMARYKEY].value ? checkedRow.rows : [];
        } else {
            return [];
        }
    }

    /**
     * 方法功能：
     *      点击查询按钮
     * @param props
     * @param data
     * @param type
     * @param queryInfo  查询方案
     */
    onSearchList = (props, data, type, queryInfo) => {
        let {list: {table}} = this.state;
        // 调用查询列表方法，通过回调处理查询结果
        this.queryListData({
            callback: (data) => { // data为查询结果
                // 执行查询列表后操作
                this.afterLoadList({
                    data,
                    callback: () => {
                        // 获取查询结果数据
                        let rowCount = data ? data[table.area].allpks.length : 0;
                        let message = { // 组装提示信息
                            color: rowCount > 0 ? 'success' : 'warning',
                            content: rowCount > 0 ? '查询成功，共 ' + rowCount + ' 条' : "未查询出符合条件的数据"
                        }
                        // 提示
                        toast(message);
                    }
                });
            }
        });
    }

    /**
     * 方法功能：
     *      加载列表数据后的操作,主要就是将查询结果填充到列表和更新按钮状态
     *      如果有其他的操作可在调用的地方通过回调的方式添加
     * @param data  grid格式数据
     * @param callback  回调
     */
    afterLoadList = ({data, callback = EMPTY_FN} = {}) => {
        let {list: {table}} = this.state;
        // 保存allpks
        this.state.list.table.allpks = data ? data[table.area].allpks : [];
        this.setState(this.state, () => {
            // 填充列表数据
            this.fillListData({data});
            // 更新按钮状态
            this.updateBtnStatus();
            callback();
        });
    }

    /**
     * 方法功能：
     *      列表行双击
     * @param record  行数据
     * @param index  行下标
     * @param props
     * @param e
     */
    onRowDoubleClickTable = (record, index, props, e) => {
        // 设置页面模式为卡片浏览态
        this.state.showmode = SHOWMODE_CARD;
        this.state.editmode = EDITMODE_BROWSE;
        // 设置显示标题栏返回按钮
        this.state.head.initShowBackBtn = true;
        this.setState(this.state, () => {
            // 获取卡片区域编码
            let area = this.getCardAreaCode();
            // 设置卡片主表单为浏览态
            this.props.form.setFormStatus(area.formId, EDITMODE_BROWSE);
            // 清空卡片数据
            this.clearCardData();
            // 获得当前双击行数据主键
            let billId = record[FIELDS.PRIMARYKEY].value;
            // 调用卡片查询方法，通过回调处理查询结果
            this.queryCardData({
                data: { // 查询参数
                    pageCode: this.config.pagecode, // pagecode
                    pk: billId, // 主键
                    ...area // 卡片区域编码
                },
                callback: (data) => { // data为查询结果
                    // 填充卡片数据或清空卡片数据
                    data ? this.fillCardData({data}) : this.clearCardData();
                    // 根据列表数据初始化卡片翻页器
                    this.initCardPaginationWithAllpks();
                    // 设置翻页器当前的数据主键
                    this.setCardPaginationId({id: billId, status: 1});
                    // 更新按钮状态
                    this.updateBtnStatus();
                }
            });
        });
    }

    /**
     * 方法功能：
     *      列表选中行变化
     */
    onListSelectRowsChanged = () => {
        // 更新按钮状态
        this.updateBtnStatus();
    }

    /**
     * 方法功能：
     *      卡片翻页
     * @param props
     * @param nextId  下一个单据id
     */
    onBtnClickCardPagniation = (props, nextId) => {
        // 获取卡片区域编码
        let area = this.getCardAreaCode();
        // 调用卡片查询方法，通过回调处理查询结果
        this.queryCardData({
            data: { // 查询参数
                pageCode: this.config.pagecode, // pagecode
                pk: nextId, // 主键
                ...area // 卡片区域编码
            },
            callback: (data) => { // data为查询结果
                // 填充卡片数据或清空卡片数据
                data ? this.fillCardData({data}) : this.clearCardData();
                // 更新按钮状态
                this.updateBtnStatus();
            }
        });
    }

    /**
     * 方法功能：
     *      点击标题返回按钮
     */
    onBackCard = () => {
        // 设置页面模式为列表浏览态
        this.state.showmode = SHOWMODE_LIST;
        this.state.editmode = EDITMODE_BROWSE;
        // 设置不显示标题栏返回按钮
        this.state.head.initShowBackBtn = false;
        this.setState(this.state, () => {
            // 调用列表查询方法，通过回调处理查询结果
            this.queryListData({
                callback: (data) => { // data为查询结果
                    // 执行查询列表后操作
                    this.afterLoadList({data});
                }
            });
        });
    }

    /**
     * 方法功能：
     *      卡片表单编辑前事件
     */
    /**
     * 方法功能：
     *      卡片表单编辑前事件
     * @param props
     * @param moduleId  区域id
     * @param key  操作的字段编码
     * @param value  当前值
     * @param data  当前表单所有值
     * @return boolean  操作字段是否可编辑
     */
    onBeforeEditForm = (props, moduleId, key, value, data) => {
        /**
         * Demo[1]参照过滤
         * 场景描述： 表单上同时有物料、物料分类两个字段，物料受物料分类过滤, 物料选择时,受到选择的物料分类进行物料参照过滤
         */
        // if (key == 'pk_material') {
        //     let meta = this.meta.getMeta();
        //     meta[moduleId].items.forEach(item => {
        //         if (item.attrcode === 'pk_material') {
        //             item.queryCondition = () => ({
        //                 GridRefActionExt: 'nccloud.web.rm.policyrule.ref.MaterialGridRefSqlBuilder',
        //                 pk_marbasclass: data.values.martype.value
        //             });
        //         }
        //     });
        //     this.props.meta.setMeta(meta);
        // }

        /**
         * Demo[2]控制字段是否可以编辑
         * 场景描述： 表单上同时有物料、物料分类两个字段，物料受物料分类过滤, 物料分类未选择时,物料无法编辑
         */
        // if (key == 'pk_material') {
        //     return data.values.martype && data.values.martype.value;
        // }
        return true;
    }

    /**
     * 方法功能：
     *      卡片表单编辑后事件
     * @param props
     * @param moduleId  区域编码
     * @param key  字段标识
     * @param value  改变后值
     * @param oldValue  改变前值
     */
    onAfterEditForm = (props, moduleId, key, value, oldValue) => {
        if (key == FIELDS.TRANSTYPEPK) { // 修改交易类型pk字段时
            // 获取改变后交易类型编码
            let transtypecode = value && value.values && value.values.pk_billtypecode || {};
            // 修改交易类型编码到交易类型字段
            this.props.form.setFormItemsValue(moduleId, {[FIELDS.TRANSTYPE]: transtypecode});
        }

        /**
         * Demo[1] 动态启用禁用属性
         * 场景：表单区上同时有部门、组织字段，组织字段填写后才可填写部门
         * 效果：填写组织后启用部门字段可编辑，清空组织后禁用部门不可编辑
         */
        // if (key == 'pk_org') {
        //     this.props.form.setFormItemsDisabled(moduleId, {'pk_dept': !(value && value.refpk)});
        // }
    }

    /**
     * 方法功能：
     *      初始化卡片翻页器,在列表切换到卡片时调用,初始化卡片翻页组件的allpks
     */
    initCardPaginationWithAllpks = () => {
        let {allpks} = this.state.list.table;
        // 设置卡片翻页器的allpks，当调用cardPagination组件的API新增、删除pk时，allpks会同步更新
        this.props.ViewModel.setData(this.config.pagecode, {
            simpleTable: {
                allpks: allpks
            }
        });
    }

    /**
     * 新增删除更新allpks，并更新缓存
     * @param id
     * @param status
     * @param callback
     */
    setCardPaginationId = ({id, status, callback = EMPTY_FN}) => {
        if (!id || !status) {
            return;
        }
        let allpks = this.state.list.table.allpks || [];
        if (status == 2) {
            allpks.push(id);
        } else if (status == 3) {
            allpks = allpks.filter(pk => pk != id);
        }
        this.props.cardPagination.setCardPaginationId({id: id, status: status});
        this.state.list.table.allpks = allpks || [];
        this.setState(this.state, () => {
            this.initCardPaginationWithAllpks();
            callback();
        });
    }

    /**
     * 方法功能：
     *      隐藏附件管理
     */
    onHideUploader = () => {
        // 设置不显示附件管理
        this.state.uploader.visible = false;
        this.setState(this.state);
    }

    /**
     * 方法功能：
     *      按钮点击事件
     * @param props
     * @param btnCode  按钮id
     * @param param  列表操作列参数，适配操作列按钮
     */
    onBtnClickHead = (props, btnCode, param = {}) => {
        switch (btnCode) {
            case ACTIONS.ADD: // 点击新增
                this.onAdd();
                break;
            case ACTIONS.EDIT: // 点击修改
                this.onEdit(param);
                break;
            case ACTIONS.DELETE: // 点击删除
                this.onDelete(param);
                break;
            case ACTIONS.REFRESH: // 点击刷新
                this.onRefresh();
                break;
            case ACTIONS.SAVE: // 点击保存
                this.onSave();
                break;
            case ACTIONS.SAVEADD: // 点击保存新增
                this.onSaveAdd();
                break;
            case ACTIONS.CANCEL: // 点击取消
                this.onCancel();
                break;
            case ACTIONS.COPY: // 点击复制
                this.onCopy();
                break;
            case ACTIONS.EXPORT: // 点击导出
                this.onExport();
                break;
            case ACTIONS.PRINT: // 点击打印
                this.onPrintOutput({isPrint: true});
                break;
            case ACTIONS.OUTPUT: // 点击输出
                this.onPrintOutput({isPrint: false});
                break;
            case ACTIONS.ATTACHMENT: // 点击附件管理
                this.onAttachment();
                break;
            // case 'ShowModal':
            //     this.props.modal.show('demoModal');
            //     break;
            default:
                break;
        }
    }

    /**
     * 方法功能：
     *      点击新增按钮
     */
    onAdd = () => {
        // 记录点击新增按钮前的页面模式
        let {showmode: oldShowmode} = this.state;
        // 设置页面模式为卡片新增态
        this.state.showmode = SHOWMODE_CARD;
        this.state.editmode = EDITMODE_ADD;
        // 设置不显示标题栏返回按钮
        this.state.head.initShowBackBtn = false;
        this.setState(this.state, () => {
            // 获取卡片区域编码
            let area = this.getCardAreaCode();
            // 设置卡片主表单为新增态
            this.props.form.setFormStatus(area.formId, 'add');
            // 清空卡片数据
            this.clearCardData();
            // 获取模板默认值
            let billCard = this.getCardData();
            // 请求默认数据
            ajax({
                url: URLS.addUrl,
                data: {
                    pageCode: this.config.pagecode, // pagecode
                    billCard: billCard, // 模板默认值
                    ...area // 卡片区域编码
                },
                success: (res) => {
                    let {data: {data} = {}} = res;
                    // 填充卡片数据
                    data && this.fillCardData({data});
                    // 更新按钮状态
                    this.updateBtnStatus();
                    // 如果是在列表页面点击新增按钮，初始化翻页器
                    oldShowmode == SHOWMODE_LIST && this.initCardPaginationWithAllpks();
                }
            });
        });
    }

    /**
     * 方法功能：
     *      点击修改按钮
     */
    onEdit = (param = {}) => {
        let {showmode} = this.state;
        // 列表操作列的参数
        let {record, index} = param;
        // 获取选中行
        let checkedRows = this.getCheckedRows();
        // 获取选中行主键，多行取第一条，适配列表操作列优先从record中取值
        let billId = record ? record[FIELDS.PRIMARYKEY].value : checkedRows[0].values[FIELDS.PRIMARYKEY].value;
        // 设置页面模式为卡片编辑态
        this.state.showmode = SHOWMODE_CARD;
        this.state.editmode = EDITMODE_EDIT;
        // 设置不显示标题栏返回按钮
        this.state.head.initShowBackBtn = false;
        this.setState(this.state, () => {
            // 获取卡片区域编码
            let area = this.getCardAreaCode();
            // 设置卡片主表单为编辑态
            this.props.form.setFormStatus(area.formId, EDITMODE_EDIT);
            if (showmode == SHOWMODE_LIST) { // 列表页面点击修改按钮时
                // 调用卡片查询方法，通过回调处理查询结果
                this.queryCardData({
                    data: {
                        pageCode: this.config.pagecode, // pagecode
                        pk: billId, // 主键
                        ...area // 区域编码
                    },
                    callback: (data) => { // data为查询结果
                        // 填充卡片数据或清空卡片数据
                        data ? this.fillCardData({data}) : this.clearCardData();
                        // 根据列表数据初始化卡片翻页器
                        this.initCardPaginationWithAllpks();
                        // 更新按钮状态
                        this.updateBtnStatus();
                    }
                });
            } else if (showmode == SHOWMODE_CARD) { // 卡片页面点击修改按钮时
                // 更新按钮状态
                this.updateBtnStatus();
            }
        });
    }

    /**
     * 方法功能：
     *      点击删除按钮
     */
    onDelete = (param = {}) => {
        // 弹框确认是否要删除
        promptBox({
            color: 'warning', // 弹框类型（success、info、warning、danger）
            title: '删除', // 标题
            content: '是否确定要删除？', // 提示内容
            beSureBtnName: '确定', // 确定按钮显示内容
            cancelBtnName: '取消', // 取消按钮显示内容
            beSureBtnClick: () => { // 点击确定按钮
                let {showmode} = this.state;
                // 列表操作列的参数
                let {record, index} = param;
                // 获取选中行
                let checkedRows = this.getCheckedRows();
                // 构建tsMap:{主键: ts}，适配列表操作列优先从record中取值
                let tsMap = {};
                record ? Object.assign(tsMap, {[record[FIELDS.PRIMARYKEY].value]: record.ts.value})
                    : checkedRows.forEach(row => Object.assign(tsMap, {[row.values[FIELDS.PRIMARYKEY].value]: row.values['ts'].value}));
                // 删除数据
                ajax({
                    url: URLS.deleteUrl,
                    data: {tsMap},
                    success: (res) => {
                        if (showmode == SHOWMODE_LIST) { // 列表页面点击删除时
                            // 删除成功调用列表查询方法，通过回调处理查询结果
                            this.queryListData({
                                callback: (data) => { // data为查询结果
                                    // 执行列表查询后操作
                                    this.afterLoadList({
                                        data,
                                        callback: () => {
                                            // 提示
                                            toast({color: 'success', content: '删除成功'});
                                        }
                                    });
                                }
                            });
                        } else if (showmode == SHOWMODE_CARD) { // 卡片页面点击删除时
                            // 获取被删除数据的主键
                            let cardId = checkedRows[0].values[FIELDS.PRIMARYKEY].value;
                            // 通过卡片翻页器API获取删除成功后的下一条数据主键
                            let nextId = this.props.cardPagination.getNextCardPaginationId({id: cardId, status: 3});
                            // 卡片翻页器删除被删除数据的主键，status（1为修改，2为新增，3为删除）
                            this.setCardPaginationId({id: cardId, status: 3});
                            if (nextId) { // 存在删除成功后下一条数据主键
                                // 获取卡片区域编码
                                let area = this.getCardAreaCode();
                                // 调用卡片查询方法，通过回调处理查询结果
                                this.queryCardData({
                                    data: {
                                        pageCode: this.config.pagecode, // pagecode
                                        pk: nextId, // 主键
                                        ...area // 区域编码
                                    },
                                    callback: (data) => { // data为查询结果
                                        // 填充卡片数据或清空卡片数据
                                        data ? this.fillCardData({data}) : this.clearCardData();
                                        // 卡片翻页器设置当前为下一条主键，status（1为修改，2为新增，3为删除）
                                        this.setCardPaginationId({id: nextId, status: 1});
                                        // 更新按钮状态
                                        this.updateBtnStatus();
                                        // 提示
                                        toast({color: 'success', content: '删除成功'});
                                    }
                                });
                            } else {
                                // 清空卡片数据
                                this.clearCardData();
                                // 更新按钮状态
                                this.updateBtnStatus();
                                // 提示
                                toast({color: 'success', content: '删除成功'});
                            }
                        }
                    }
                });
            }
        });
    }

    /**
     * 方法功能：
     *      点击刷新按钮
     */
    onRefresh = () => {
        let {showmode} = this.state;
        if (showmode == SHOWMODE_LIST) { // 列表页面点击刷新时
            // 调用列表查询方法，通过回调处理查询结果
            this.queryListData({
                callback: (data) => { // data为查询结果
                    // 执行列表查询后操作
                    this.afterLoadList({
                        data,
                        callback: () => {
                            // 提示
                            toast({color: 'success', content: '刷新成功'});
                        }
                    });
                }
            });
        } else if (showmode == SHOWMODE_CARD) { // 卡片页面点击刷新时
            // 获取选中行
            let checkedRows = this.getCheckedRows();
            // 获取选中行主键
            let billId = checkedRows[0].values[FIELDS.PRIMARYKEY].value;
            // 获取卡片区域编码
            let area = this.getCardAreaCode();
            // 调用卡片查询方法，通过回调处理查询结果
            this.queryCardData({
                data: {
                    pageCode: this.config.pagecode, // pagecode
                    pk: billId, // 主键
                    ...area // 区域编码
                },
                callback: (data) => { // data为查询结果
                    // 填充卡片数据或清空卡片数据
                    data ? this.fillCardData({data}) : this.clearCardData();
                    // 更新按钮状态
                    this.updateBtnStatus();
                    // 提示
                    toast({color: 'success', content: '刷新成功'});
                }
            });
        }
    }

    /**
     * 方法功能：
     *      点击保存按钮
     */
    onSave = (param = {}, callback = EMPTY_FN) => {
        // 获取卡片区域编码
        let area = this.getCardAreaCode();
        // 卡片主表单必输项校验
        if (!this.props.form.isCheckNow(area.formId)) return;
        // 根据卡片主表单主键字段是否有值标记当前保存操作是新增还是修改
        let isAdd = !(this.props.form.getFormItemsValue(area.formId, FIELDS.PRIMARYKEY).value);
        // 获取卡片填写数据
        let saveData = this.getCardData();
        // 校验公式参数
        let areainfo = {
            [area.formId]: 'form'
        };
        // 保存前执行校验公式
        this.props.validateToSave(saveData, () => {
            // 保存请求
            ajax({
                url: URLS.saveUrl,
                data: {
                    billCard: saveData, // 卡片数据
                    pageCode: this.config.pagecode, // pagecode
                    ...area // 区域编码
                },
                success: (res) => {
                    let {data: {data} = {}} = res;
                    let {showmode} = this.state;
                    // 设置编辑模式为编辑态，不需要切换页面模式
                    this.state.editmode = EDITMODE_BROWSE;
                    // 如果是卡片页面设置显示标题栏返回按钮
                    this.state.head.initShowBackBtn = showmode == SHOWMODE_CARD;
                    this.setState(this.state, () => {
                        // 设置卡片主表单为浏览态
                        this.props.form.setFormStatus(area.formId, EDITMODE_BROWSE);
                        // 填充卡片数据或清空卡片数据
                        data ? this.fillCardData({data}) : this.clearCardData();
                        // 更新按钮状态
                        this.updateBtnStatus();
                        // 获取保存后数据主键
                        let billId = data.head[area.formId].rows[0].values[FIELDS.PRIMARYKEY].value;
                        // 设置卡片翻页器当前主键，status（1为修改，2为新增，3为删除）
                        this.setCardPaginationId({id: billId, status: isAdd ? 2 : 1});
                        // 提示
                        toast({color: 'success', content: '保存成功'});
                        callback(data);
                    });
                }
            });
        }, areainfo, 'extcard');
    }

    /**
     * 方法功能：
     *      点击保存新增
     */
    onSaveAdd = () => {
        // 获取卡片区域编码
        let area = this.getCardAreaCode();
        // 卡片主表单必输项校验
        if (!this.props.form.isCheckNow(area.formId)) return;
        // 根据卡片主表单主键字段是否有值标记当前保存操作是新增还是修改
        let isAdd = !(this.props.form.getFormItemsValue(area.formId, FIELDS.PRIMARYKEY).value);
        // 获取卡片填写数据
        let saveData = this.getCardData();
        // 校验公式参数
        let areainfo = {
            [area.formId]: 'form'
        };
        // 保存前执行校验公式
        this.props.validateToSave(saveData, () => {
            // 保存请求
            ajax({
                url: URLS.saveUrl,
                data: {
                    billCard: saveData, // 卡片数据
                    pageCode: this.config.pagecode, // pagecode
                    ...area // 区域编码
                },
                success: (res) => {
                    let {data: {data} = {}} = res;
                    // 获取保存后数据主键
                    let billId = data.head[area.formId].rows[0].values[FIELDS.PRIMARYKEY].value;
                    // 设置卡片翻页器当前主键，status（1为修改，2为新增，3为删除）
                    this.setCardPaginationId({id: billId, status: isAdd ? 2 : 1});
                    // 提示
                    toast({color: 'success', content: '保存成功'});
                    // 调用点击新增
                    this.onAdd();
                }
            });
        }, areainfo, 'extcard');
    }

    /**
     * 方法功能：
     *      点击取消按钮
     */
    onCancel = () => {
        promptBox({
            color:'warning', // 弹框类型（success、info、warning、danger）
            title: '取消', // 标题
            content: '是否确认要取消？', // 提示内容
            beSureBtnName: '确定', // 确定按钮显示内容
            cancelBtnName: '取消', // 取消按钮显示内容
            beSureBtnClick : () => { // 点击确定按钮
                // 设置页面模式为卡片浏览态
                this.state.showmode = SHOWMODE_CARD;
                this.state.editmode = EDITMODE_BROWSE;
                // 设置显示标题栏返回按钮
                this.state.head.initShowBackBtn = true;
                this.setState(this.state, () => {
                    let {allpks} = this.state.list.table;
                    let billId = undefined;
                    // 获取卡片区域编码
                    let area = this.getCardAreaCode();
                    // 设置卡片主表单为浏览态
                    this.props.form.setFormStatus(area.formId, EDITMODE_BROWSE);
                    // 先从卡片主表单获取主键，如果有代表是修改取消，重新加载当前单据
                    billId = this.props.form.getFormItemsValue(area.formId, FIELDS.PRIMARYKEY).value;
                    if (!billId && allpks && allpks.length > 0) { // 如果是新增取消加载最后一条单据
                        billId = allpks[allpks.length - 1];
                    }
                    if (billId) {
                        // 调用卡片查询方法，通过回调处理查询结果
                        this.queryCardData({
                            data: {
                                pageCode: this.config.pagecode, // pagecode
                                pk: billId, // 主键
                                ...area // 区域编码
                            },
                            callback: (data) => { // data为查询结果
                                // 填充卡片数据或清空卡片数据
                                data ? this.fillCardData({data}) : this.clearCardData();
                                // 设置卡片翻页器当前主键，status（1为修改，2为新增，3为删除）
                                this.setCardPaginationId({id: billId, status: 1});
                                // 更新按钮状态
                                this.updateBtnStatus();
                            }
                        });
                    } else { // 不存在可加载的单据
                        // 清空卡片数据
                        this.clearCardData();
                        // 更新按钮状态
                        this.updateBtnStatus();
                    }
                });
            }
        })
    }

    /**
     * 方法功能：
     *      点击复制按钮
     */
    onCopy = () => {
        let {showmode: oldShowmode} = this.state;
        // 获取选中行
        let checkedRows = this.getCheckedRows();
        // 获取选中行主键，多行取第一条，适配列表操作列优先从record中取值
        let billId = checkedRows[0].values[FIELDS.PRIMARYKEY].value;
        // 设置页面模式为卡片编辑态
        this.state.showmode = SHOWMODE_CARD;
        this.state.editmode = EDITMODE_EDIT;
        // 设置不显示标题栏返回按钮
        this.state.head.initShowBackBtn = false;
        this.setState(this.state, () => {
            // 获取卡片区域编码
            let area = this.getCardAreaCode();
            // 设置卡片主表单为编辑态
            this.props.form.setFormStatus(area.formId, EDITMODE_EDIT);
            ajax({
                url: URLS.copyUrl,
                data: {
                    pk: billId, // 主键
                    pageCode: this.config.pagecode, // pagecode
                    ...area // 区域编码
                },
                success: (res = {}) => {
                    let {data: {data} = {}} = res;
                    data ? this.fillCardData({data}) : this.clearCardData();
                    // 更新按钮状态
                    this.updateBtnStatus();
                    // 根据列表数据初始化卡片翻页器
                    oldShowmode == SHOWMODE_LIST && this.initCardPaginationWithAllpks();
                }
            });
        });
    }

    /**
     * 方法功能：
     *      点击导出按钮
     */
    onExport = () => {
        // 获取选中行
        let checkedRows = this.getCheckedRows();
        // 遍历得到选中行pk数组
        let pks = checkedRows ? checkedRows.map(row => row.values[FIELDS.PRIMARYKEY].value) : [];
        // 设置导出组件的选中行pks
        this.state.execlExport.selectedPKS = pks;
        this.setState(this.state, () => {
            // 显示导出组件
            this.props.modal.show('exportFileModal');
        });
    }

    /**
     * 方法功能：
     *      打印输出
     */
    onPrintOutput = (param = {}) => {
        let {isPrint} = param;
        let {showmode} = this.state;
        // 获取选中行
        let checkedRows = this.getCheckedRows();
        // 遍历得到选中行pk数组
        let pks = checkedRows ? checkedRows.map(row => row.values[FIELDS.PRIMARYKEY].value) : [];
        // 根据不同页面模式获取不同的打印模板标识
        let nodekey = showmode == SHOWMODE_LIST ? PRINTNODEKEY_LIST : PRINTNODEKEY_CARD;
        // 设置打印输出配置
        this.state.printOutput.data.oids = pks;
        this.state.printOutput.data.nodekey = nodekey;
        this.state.printOutput.data.outputType = isPrint ? 'print' : 'output'; // 是打印还是输出
        this.setState(this.state, () => {
            if (isPrint) { // 点击打印时
                let {printOutput} = this.state;
                // 调用打印API
                print('pdf', printOutput.url, printOutput.data);
            } else { // 点击输出时
                // 显示输出组件
                this.refs.printOutput.open();
            }
        })
    }

    /**
     * 方法功能：
     *      附件管理
     */
    onAttachment = () => {
        // 获取选中行
        let checkedRows = this.getCheckedRows();
        // 获取主键，选中多行取第一行
        let billId = checkedRows && checkedRows.length > 0 ? checkedRows[0].values[FIELDS.PRIMARYKEY].value : undefined;
        // 设置显示附件管理组件
        this.state.uploader.visible = true;
        // 设置附件管理组件的主键
        this.state.uploader.billId = billId;
        this.setState(this.state);
    }


    /**
     * 方法功能：
     *      列表行单击
     * @param props
     * @param moduleId  表格id
     * @param record  行数据
     * @param index  行下标
     * @param e
     */
    onRowClickTable = (props, moduleId, record, index, e) => {
    }

	

}


const config = {
    title: 'API日志信息',
    pagecode: 'MKH30101_pub_clog',
    appcode: 'MKH30101',
    domainName: 'baseapp',
    version: 2005
};
ApplicationPage = createPage({
    initTemplate:{},
    billinfo: {
        billtype: 'extcard',
        pagecode: 'MKH30101_pub_clog',
        headcode: 'pub_clog_form',
    }
})(ApplicationPage);
ReactDOM.render(<ApplicationPage {...config}/>, document.querySelector("#app"));