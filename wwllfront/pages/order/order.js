// pages/order/order.js
Page({
  data: {
      quotes: [], // 历史报价数据
      recentOrders: [], // 最近订单数据
      mappings: {
          modelMapping: {
              1: "3#",
              2: "4#",
              3: "5#"
          },
          materialMapping: {
              1: "尼龙",
              2: "黄铜",
              3: "不锈钢"
          },
          openCloseMapping: {
              1: "开口",
              2: "闭口"
          }
      },
      currentPage: 1, // 当前页码
      pageSize: 10,   // 每页数量
      hasNextPage: true, // 是否有下一页
      searchId: '', // 用户输入的搜索 ID
      searchResult: null, // 搜索结果
      showModal: false, // 控制模态框显示隐藏
      showConfirmModal: false, // 控制确认对话框显示隐藏
      selectedOrderIndex: null // 选中订单的索引
  },

  onLoad() {
      this.fetchOrdersByPage();
  },

  // 格式化订单数据
  formatOrders(orders) {
      const { modelMapping, materialMapping, openCloseMapping } = this.data.mappings;
      return orders.map(order => ({
          model: modelMapping[order.modelId] || "未知型号",
          material: materialMapping[order.materialId] || "未知材质",
          openClose: openCloseMapping[order.openCloseId] || "未知",
          length: order.length ? order.length + "公分" : "未知长度",
          quantity: order.quantity ? order.quantity + "条" : "未知数量",
          price: order.totalPrice || "未知总价"
      }));
  },

  // 分页获取订单数据
  fetchOrdersByPage() {
      const { currentPage, pageSize } = this.data;
      wx.request({
          url: `http://localhost:8080/api/orders/page?current=${currentPage}&size=${pageSize}`,
          method: 'GET',
          header: { 'content-type': 'application/json' },
          success: (res) => {
              if (res.data.code === 200) {
                  const orders = res.data.data.records;
                  const formattedQuotes = this.formatOrders(orders);
                  this.setData({
                      quotes: this.data.quotes.concat(formattedQuotes),
                      recentOrders: this.data.recentOrders.concat(orders),
                      hasNextPage: res.data.data.hasNextPage // 根据后端返回判断是否有下一页
                  });
              } else {
                  wx.showToast({ title: res.data.message, icon: 'none' });
              }
          },
          fail: (err) => {
              wx.showToast({ title: '请求订单失败', icon: 'none' });
          }
      });
  },

  // 加载下一页数据
  loadMore() {
      if (this.data.hasNextPage) {
          this.setData({
              currentPage: this.data.currentPage + 1
          });
          this.fetchOrdersByPage();
      }
  },

  // 输入框输入事件处理函数
  onInputChange(e) {
      this.setData({
          searchId: e.detail.value
      });
  },

  // 搜索按钮点击事件处理函数
  searchOrder() {
      const searchId = this.data.searchId;
      if (!searchId) {
          wx.showToast({ title: '请输入订单 ID', icon: 'none' });
          return;
      }
      wx.request({
          url: `http://localhost:8080/api/orders/${searchId}`,
          method: 'GET',
          header: { 'content-type': 'application/json' },
          success: (res) => {
              if (res.data.code === 200) {
                  const order = res.data.data;
                  const formattedOrder = this.formatOrders([order])[0];
                  this.setData({
                      searchResult: formattedOrder,
                      showModal: true
                  });
              } else {
                  wx.showToast({ title: res.data.message, icon: 'none' });
                  this.setData({
                      searchResult: null,
                      showModal: false
                  });
              }
          },
          fail: (err) => {
              wx.showToast({ title: '搜索订单失败', icon: 'none' });
              this.setData({
                  searchResult: null,
                  showModal: false
              });
          }
      });
  },

  // 关闭模态框
  closeModal() {
      this.setData({
          showModal: false
      });
  },

  // 显示确认对话框
  showConfirmModal(e) {
      const index = e.currentTarget.dataset.index;
      this.setData({
          showConfirmModal: true,
          selectedOrderIndex: index
      });
  },

  // 取消生成图片
  cancelGenerateImage() {
      this.setData({
          showConfirmModal: false
      });
  },

// 确认生成图片
confirmGenerateImage() {
  const { quotes, selectedOrderIndex } = this.data;
  const order = quotes[selectedOrderIndex];
  const ctx = wx.createCanvasContext('quoteCanvas');
  const canvasSize = 400; // 设置 canvas 为正方形，边长 400px

  // 设置背景颜色
  ctx.setFillStyle('#f0f0f0'); // 这里使用浅灰色作为背景色，你可以根据需要修改
  ctx.fillRect(0, 0, canvasSize, canvasSize);

  // 设置字体和样式
  ctx.setFontSize(16);
  ctx.setFillStyle('#000');

  // 定义一个函数用于绘制带偏移量的文字
  function drawCenteredText(text, y, offset = 0) {
      const textWidth = ctx.measureText(text).width;
      const x = (canvasSize - textWidth) / 2 + offset;
      ctx.fillText(text, x, y);
  }

  const offsetValue = -50; // 向左偏移 50px，你可以根据需要调整这个值
  const verticalOffset = 30; // 整体内容向下偏移 30px，你可以根据需要调整这个值

  // 绘制标题
  ctx.setFontSize(20); // 标题字体稍大
  drawCenteredText('今日拉链报价', 20 + verticalOffset, offsetValue);

  // 恢复字体大小
  ctx.setFontSize(16);

  // 绘制报价信息，调整文字布局
  drawCenteredText(`型号: ${order.model}`, 60 + verticalOffset, offsetValue);
  drawCenteredText(`材质: ${order.material}`, 90 + verticalOffset, offsetValue);
  drawCenteredText(`开闭口: ${order.openClose}`, 120 + verticalOffset, offsetValue);
  drawCenteredText(`长度: ${order.length}`, 150 + verticalOffset, offsetValue);
  drawCenteredText(`数量: ${order.quantity}`, 180 + verticalOffset, offsetValue);
  drawCenteredText(`总价: ${order.price}`, 210 + verticalOffset, offsetValue);

  // 处理数量和总价，提取数字部分
  const quantityStr = String(order.quantity).replace('条', '');
  const priceStr = String(order.price).replace('未知总价', '0');

  const quantity = parseFloat(quantityStr);
  const totalPrice = parseFloat(priceStr);

  let unitPriceText;
  if (!isNaN(quantity) && !isNaN(totalPrice) && quantity > 0) {
      const unitPrice = (totalPrice / quantity).toFixed(2);
      unitPriceText = `单价: ${unitPrice} 元`;
  } else {
      unitPriceText = '单价: 未知';
  }

  drawCenteredText(unitPriceText, 240 + verticalOffset, offsetValue);

  // 添加水印
  ctx.setFontSize(30); // 设置水印字体大小
  ctx.setFillStyle('rgba(0, 0, 0, 0.2)'); // 设置水印颜色和透明度
  ctx.textAlign = 'center'; // 设置文字居中对齐
  ctx.textBaseline = 'middle'; // 设置文字垂直居中对齐
  ctx.rotate(-Math.PI / 4); // 旋转 45 度
  const watermarkText = '伟伟拉链';
  const watermarkX = canvasSize / 2 + offsetValue-160;
  const watermarkY = canvasSize / 2 + verticalOffset;
  ctx.fillText(watermarkText, watermarkX, watermarkY);
  ctx.rotate(Math.PI / 4); // 恢复旋转

  // 绘制到 canvas
  ctx.draw(false, () => {
      wx.canvasToTempFilePath({
          canvasId: 'quoteCanvas',
          success: res => {
              wx.saveImageToPhotosAlbum({
                  filePath: res.tempFilePath,
                  success: () => {
                      wx.showToast({ title: '导出成功', icon: 'success' });
                      this.setData({
                          showConfirmModal: false
                      });
                  },
                  fail: () => {
                      wx.showToast({ title: '导出失败', icon: 'none' });
                  }
              });
          },
          fail: () => {
              wx.showToast({ title: '生成图片失败', icon: 'none' });
          }
      });
  });
}
});