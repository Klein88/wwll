// index.js
Page({
  data: {
    quotes: [], // 历史报价数据
    recentOrders: [], // 最近订单数据
    mappings: {
      modelMapping: {
        1: "3#", 2: "4#", 3: "5#"
      },
      materialMapping: {
        1: "尼龙", 2: "黄铜", 3: "不锈钢"
      },
      openCloseMapping: {
        1: "开口",
        2: "闭口"
      }
    }
  },

  onLoad() {
    wx.request({
      url: 'http://localhost:8080/api/orders/recent',
      method: 'GET',
      header: { 'content-type': 'application/json' },
      success: (res) => {
        if (res.data.code === 200) {
          const orders = res.data.data;
          const { modelMapping, materialMapping, openCloseMapping } = this.data.mappings; // 取出映射表

          const formattedQuotes = orders.map(order => ({
            model: modelMapping[order.modelId] || "未知型号",  // 映射型号
            material: materialMapping[order.materialId] || "未知材质",  // 映射材质
            openClose: openCloseMapping[order.openCloseId] || "未知",  // 映射开闭口
            length: order.length ? order.length + "公分" : "未知长度",  
            quantity: order.quantity ? order.quantity + "条" : "未知数量",  
            price: order.totalPrice || "未知单价"
          }));

          // console.log("formattedQuotes:", formattedQuotes); // 打印检查格式化后的数据
          this.setData({ quotes: formattedQuotes, recentOrders: orders });
        } else {
          wx.showToast({ title: res.data.message, icon: 'none' });
        }
      },
      fail: (err) => {
        wx.showToast({ title: '请求订单失败', icon: 'none' });
      }
    });
    
  },
  goToQuotePage1() {
    wx.switchTab({
      url: '/pages/quote/quote'  // 路径是根据你的项目结构设置的
    });
  },
  goToQuotePage2() {
    wx.switchTab({
      url: '/pages/order/order'  // 路径是根据你的项目结构设置的
    });
  },
  goToQuotePage3() {
    wx.switchTab({
      url: '/pages/material/material'  // 路径是根据你的项目结构设置的
    });
  }
});