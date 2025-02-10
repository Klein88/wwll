// pages/material/material.js
Page({
  data: {
    materials: [], // 材料列表数据
    searchKeyword: '', // 搜索关键字
    isFormVisible: false, // 表单对话框是否可见
    isEdit: false, // 是否为编辑状态
    formData: {
      id: null,
      modelId: null,
      name: '',
      typeId: null,
      rollPrice: ''
    },
    mappings: {
      modelMapping: {
        1: "3#", 2: "4#", 3: "5#",4:"8#"
      },
      typeMapping: {
        1: "开口",
        2: "闭口"
      }
    },
    // 筛选相关属性（注意：这里的选项值仅为示例，实际可根据业务从接口加载或自行定义）
    modelOptions: ["全部型号", "3#", "4#", "5#", "8#"],
    typeOptions: ["全部类型", "开口", "闭口"],
    modelPickerIndex: 0,
    typePickerIndex: 0,
    selectedModel: null,
    selectedType: null,
    selectedModelName: "全部型号",
    selectedTypeName: "全部类型",
    nameOptions: ["全部名称", "白铝", "仿铜", "黄铜", "青古铜", "镀白铜", "镀白金", "白金", "黑金", "黄金"],
    namePickerIndex: 0,
    selectedName: null,
    selectedNameText: "全部名称",
  },

  onLoad() {
    // 页面加载时先获取数据，默认没有筛选条件
    this.fetchMaterials();
  },

  // 获取材料列表
  fetchMaterials() {
    let url = 'http://localhost:8080/api/materials';
    let params = [];
    if (this.data.selectedModel != null) {
      params.push('modelId=' + this.data.selectedModel);
    }
    if (this.data.selectedType != null) {
      params.push('typeId=' + this.data.selectedType);
    }
    if (this.data.namePickerIndex !== 0) {
      params.push('name=' + encodeURIComponent(this.data.selectedName));
    }
    if (params.length > 0) {
      url += '?' + params.join('&');
    }

    wx.request({
      url: url,
      method: 'GET',
      header: { 'content-type': 'application/json' },
      success: (res) => {
        console.log('获取材料列表请求成功，响应数据：', res.data);
        if (res.data.code === 200) {
          const materials = res.data.data;
          const { modelMapping, typeMapping } = this.data.mappings;

          const formattedMaterials = materials.map(material => ({
            ...material,
            model: modelMapping[material.modelId] || "未知型号",
            type: typeMapping[material.typeId] || "未知类型"
          }));

          console.log('格式化后的材料列表数据：', formattedMaterials);
          this.setData({
            materials: formattedMaterials
          });
        } else {
          wx.showToast({
            title: res.data.message || '获取材料列表失败',
            icon: 'none'
          });
        }
      },
      fail: (err) => {
        console.log('获取材料列表请求失败，错误信息：', err);
        wx.showToast({
          title: '网络请求失败',
          icon: 'none'
        });
      }
    });
  },

  // 搜索输入事件
  onSearchInput(e) {
    this.setData({
      searchKeyword: e.detail.value
    });
  },

  // 搜索按钮点击事件
  onSearch() {
    const keyword = this.data.searchKeyword;
    // 这里假设后端有模糊搜索接口，实际需根据后端实现调整
    wx.request({
      url: `http://localhost:8080/api/materials/search?keyword=${keyword}`,
      method: 'GET',
      success: (res) => {
        console.log('搜索材料请求成功，响应数据：', res.data);
        if (res.data.code === 200) {
          const materials = res.data.data;
          const { modelMapping, typeMapping } = this.data.mappings;

          const formattedMaterials = materials.map(material => ({
            ...material,
            model: modelMapping[material.modelId] || "未知型号",
            type: typeMapping[material.typeId] || "未知类型"
          }));

          console.log('搜索并格式化后的材料列表数据：', formattedMaterials);
          this.setData({
            materials: formattedMaterials
          });
        } else {
          wx.showToast({
            title: res.data.message || '搜索材料失败',
            icon: 'none'
          });
        }
      },
      fail: (err) => {
        console.log('搜索材料请求失败，错误信息：', err);
        wx.showToast({
          title: '网络请求失败',
          icon: 'none'
        });
      }
    });
  },

  // 新增按钮点击事件
  onAdd() {
    this.setData({
      isFormVisible: true,
      isEdit: false,
      formData: {
        id: null,
        modelId: null,
        name: '',
        typeId: null,
        rollPrice: ''
      }
    });
  },

  // 编辑按钮点击事件
  onEdit(e) {
    const id = e.currentTarget.dataset.id;
    wx.request({
      url: `http://localhost:8080/api/materials/${id}`,
      method: 'GET',
      success: (res) => {
        console.log('获取单个材料详情请求成功，响应数据：', res.data);
        if (res.data.code === 200) {
          const material = res.data.data;
          this.setData({
            isFormVisible: true,
            isEdit: true,
            formData: {
              id: material.id,
              modelId: material.modelId,
              name: material.name,
              typeId: material.typeId,
              rollPrice: material.rollPrice
            }
          });
        } else {
          wx.showToast({
            title: res.data.message || '获取材料详情失败',
            icon: 'none'
          });
        }
      },
      fail: (err) => {
        console.log('获取单个材料详情请求失败，错误信息：', err);
        wx.showToast({
          title: '网络请求失败',
          icon: 'none'
        });
      }
    });
  },

  // 型号 ID 输入事件
  onModelIdInput(e) {
    const formData = { ...this.data.formData, modelId: parseInt(e.detail.value) || null };
    this.setData({ formData });
  },

  // 名称输入事件
  onNameInput(e) {
    const formData = { ...this.data.formData, name: e.detail.value };
    this.setData({ formData });
  },

  // 类型 ID 输入事件
  onTypeIdInput(e) {
    const formData = { ...this.data.formData, typeId: parseInt(e.detail.value) || null };
    this.setData({ formData });
  },

  // 价格输入事件
  onRollPriceInput(e) {
    const formData = { ...this.data.formData, rollPrice: e.detail.value };
    this.setData({ formData });
  },

  // 提交按钮点击事件
  onSubmit() {
    const { isEdit, formData } = this.data;
    const url = isEdit ? `http://localhost:8080/api/materials/${formData.id}` : 'http://localhost:8080/api/materials';
    const method = isEdit ? 'PUT' : 'POST';
    console.log(`发起 ${method} 请求，请求 URL：${url}，请求数据：`, formData);
    wx.request({
      url,
      method,
      data: formData,
      header: {
        'content-type': 'application/json'
      },
      success: (res) => {
        console.log('提交材料信息请求成功，响应数据：', res.data);
        if (res.data.code === 200) {
          this.setData({
            isFormVisible: false
          });
          this.fetchMaterials();
          wx.showToast({
            title: isEdit ? '更新材料成功' : '新增材料成功',
            icon: 'success'
          });
        } else {
          wx.showToast({
            title: res.data.message || (isEdit ? '更新材料失败' : '新增材料失败'),
            icon: 'none'
          });
        }
      },
      fail: (err) => {
        console.log('提交材料信息请求失败，错误信息：', err);
        wx.showToast({
          title: '网络请求失败',
          icon: 'none'
        });
      }
    });
  },

  // 取消按钮点击事件
  onCancel() {
    this.setData({
      isFormVisible: false
    });
  },

  // 筛选下拉菜单事件处理
  onModelPickerChange(e) {
    const index = parseInt(e.detail.value);
    // 如果 index 为 0 表示"全部型号"，否则返回实际的 modelId（这里 index 对应实际 modelId：1,2,3,4）
    let selectedModel = (index !== 0) ? index : null;
    let selectedModelName = this.data.modelOptions[index];
    this.setData({
      modelPickerIndex: index,
      selectedModel: selectedModel,
      selectedModelName: selectedModelName
    });
    this.fetchMaterials();
  },

  onTypePickerChange(e) {
    const index = parseInt(e.detail.value);
    // 如果 index 为 0 表示"全部类型"，否则将实际 typeId 设置为 index（与后端对应：1 表示开口，2 表示闭口）
    let selectedType = (index !== 0) ? index : null;
    let selectedTypeName = this.data.typeOptions[index];
    this.setData({
      typePickerIndex: index,
      selectedType: selectedType,
      selectedTypeName: selectedTypeName
    });
    this.fetchMaterials();
  },

  resetFilters() {
    this.setData({
      modelPickerIndex: 0,
      typePickerIndex: 0,
      namePickerIndex: 0,
      selectedModel: null,
      selectedType: null,
      selectedName: null,
      selectedModelName: this.data.modelOptions[0],
      selectedTypeName: this.data.typeOptions[0],
      selectedNameText: this.data.nameOptions[0]
    });
    this.fetchMaterials();
  },

  onNamePickerChange(e) {
    const index = parseInt(e.detail.value);
    let selectedName = null;
    let selectedNameText = this.data.nameOptions[index];
    if (index !== 0) {
      selectedName = this.data.nameOptions[index];
    }
    this.setData({
      namePickerIndex: index,
      selectedName: selectedName,
      selectedNameText: selectedNameText
    });
    this.fetchMaterials();
  },
});