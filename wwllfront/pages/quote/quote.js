Page({
  data: {
    models: [],
    materials: [],
    openCloseTypes: [],
    selectedModelId: null,
    selectedModelName: '',
    selectedMaterialId: null,
    selectedMaterialName: '',
    selectedOpenCloseId: null,
    length: '',
    quantity: '',
    profitRate: '',
    result: null,
    showModal: false, // 控制对话框显示
  },

  onLoad() {
    this.fetchOptions();
  },

  fetchOptions() {
    wx.showLoading({ title: '加载中...' });
    Promise.all([
      this.fetchModels(),
      this.fetchMaterials(),
      this.fetchOpenCloseTypes()
    ]).then(() => {
      wx.hideLoading();
    }).catch(() => {
      wx.hideLoading();
    });
  },

  fetchModels() {
    return this.fetchData('http://localhost:8080/api/models', 'models');
  },

  fetchMaterials() {
    return this.fetchData('http://localhost:8080/api/materials', 'materials');
  },

  fetchOpenCloseTypes() {
    return this.fetchData('http://localhost:8080/api/open-close-types', 'openCloseTypes');
  },

  fetchData(url, key) {
    return new Promise((resolve, reject) => {
      wx.request({
        url,
        method: 'GET',
        header: { 'content-type': 'application/json' },
        success: res => {
          if (res.data.code === 200) {
            this.setData({ [key]: res.data.data });
            resolve();
          } else {
            wx.showToast({ title: res.data.message, icon: 'none' });
            reject();
          }
        },
        fail: () => {
          wx.showToast({ title: `请求${key}数据失败`, icon: 'none' });
          reject();
        }
      });
    });
  },

  pickerModelChange(e) {
    const index = e.detail.value;
    this.setData({
      selectedModelId: this.data.models[index].id,
      selectedModelName: this.data.models[index].name
    });
  },

  pickerMaterialChange(e) {
    const index = e.detail.value;
    this.setData({
      selectedMaterialId: this.data.materials[index].id,
      selectedMaterialName: this.data.materials[index].name
    });
  },
  radioOpenCloseChange(e) {
    this.setData({ selectedOpenCloseId: Number(e.detail.value) });
  },

  inputLength(e) {
    this.setData({ length: e.detail.value });
  },
  inputQuantity(e) {
    this.setData({ quantity: e.detail.value });
  },
  inputProfitRate(e) {
    let profitRateInput = e.detail.value;
    
    // 验证输入是否为有效的数字
    if (isNaN(profitRateInput) || parseFloat(profitRateInput) < 1) {
      wx.showToast({ title: '请输入不小于1的数字', icon: 'none' });
      return;
    }
    // 转换为百分制显示
    let profitRateDisplay = parseFloat(profitRateInput);
    
    // 计算实际传给后端的值
    let profitRateToBackend = profitRateDisplay / 100;
    
    this.setData({
      profitRate: profitRateDisplay,  // 用于页面显示
      profitRateToBackend: profitRateToBackend  // 用于传给后端
    });
  },
  calculateQuote() {
    if (!this.data.selectedModelId || !this.data.selectedMaterialId || !this.data.selectedOpenCloseId ||
        !this.data.length || !this.data.quantity || !this.data.profitRateToBackend) {  // 使用 profitRateToBackend 进行判断
      wx.showToast({ title: '请填写完整信息', icon: 'none' });
      return;
    }
    wx.showLoading({ title: '计算中...' });
    const requestData = {
      modelId: this.data.selectedModelId,
      materialId: this.data.selectedMaterialId,
      openCloseId: this.data.selectedOpenCloseId,
      length: Number(this.data.length),
      quantity: Number(this.data.quantity),
      profitRate: this.data.profitRateToBackend  // 使用 profitRateToBackend 传递给后端
    };
    wx.request({
      url: 'http://localhost:8080/api/quote/calculate-without-saving',
      method: 'POST',
      data: requestData,
      header: { 'content-type': 'application/json' },
      success: (res) => {
        wx.hideLoading();
        if (res.data.code === 200) {
          let result = res.data.data;
          const totalPrice = Number(result.totalPrice);
          const quantity = Number(this.data.quantity);
          const unitPrice = quantity ? totalPrice / quantity : 0;
          this.setData({
            result: { ...result, unitPrice: unitPrice.toFixed(2), totalPrice },
            showModal: true
          });
        } else {
          wx.showToast({ title: res.data.message, icon: 'none' });
        }
      },
      fail: () => {
        wx.hideLoading();
        wx.showToast({ title: '请求报价失败', icon: 'none' });
      }
    });
  },
  saveQuote() {
    wx.request({
      url: 'http://localhost:8080/api/quote', // 调用保存订单接口
      method: 'POST',
      data: {
        modelId: this.data.selectedModelId,
        materialId: this.data.selectedMaterialId,
        openCloseId: this.data.selectedOpenCloseId,
        length: Number(this.data.length),
        quantity: Number(this.data.quantity),
        profitRate: this.data.profitRateToBackend,  // 使用 profitRateToBackend 传递给后端
        totalPrice: this.data.result.totalPrice,
        unitPrice: this.data.result.unitPrice
      },
      header: { 'content-type': 'application/json' },
      success: res => {
        if (res.data.code === 200) {
          wx.showToast({ title: '保存成功', icon: 'success' });
          this.setData({ showModal: false }); // 关闭对话框
        } else {
          wx.showToast({ title: res.data.message, icon: 'none' });
        }
      },
      fail: () => {
        wx.showToast({ title: '保存失败', icon: 'none' });
      }
    });
  },
  closeModal() {
    this.setData({ showModal: false });
  }
});