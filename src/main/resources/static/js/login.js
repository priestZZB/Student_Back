$(function() {
    // 初始化显示第一个标签页
    $('.tab_content').removeClass('active').eq(0).addClass('active');

    // Tab切换功能
    $('.tab_menu li').on('click', function() {
        const index = $(this).index();
        // 切换菜单状态
        $(this).addClass('selected').siblings().removeClass('selected');
        // 切换内容区域
        $('.tab_content').removeClass('active').eq(index).addClass('active');
    });

    // 表单提交处理
    $('.login_form').on('submit', function(e) {
        e.preventDefault();
        const form = $(this);
        const type = form.attr('id').split('_')[0];
        const errorBox = $(`#${type}_error`);

        // 清空错误提示
        errorBox.text('');
        form.find('input').removeClass('error');

        // 表单验证
        let isValid = true;
        form.find('[data-rule]').each(function() {
            const $input = $(this);
            const value = $input.val().trim();
            const rules = $input.data('rule').split('|');

            for (const rule of rules) {
                if (rule === 'required' && value === '') {
                    isValid = false;
                    $input.addClass('error');
                } else if (rule.startsWith('length')) {
                    const [min, max] = rule.match(/\d+/g).map(Number);
                    if (value.length < min || value.length > max) {
                        isValid = false;
                        $input.addClass('error');
                    }
                }
            }
        });

        if (!isValid) {
            errorBox.text('请检查输入内容是否符合要求');
            return;
        }

        // 发送登录请求
        const urlMap = {
            messenger: '/login/messenger',
            teacher: '/login/processTeacher',
            admin: '/login/charge'
        };

        $.ajax({
            url: urlMap[type],
            method: 'GET',
            data: form.serialize(),
            success: function(res) {
                if (res && res.success) {
                    window.location.href = `/${type}`;  // 跳转到对应主页
                } else {
                    errorBox.text(res.message || '登录失败，请检查账号密码');
                }
            },
            error: function() {
                errorBox.text('网络请求失败，请稍后重试');
            }
        });
    });

    // 背景轮播功能
    let currentSlide = 0;
    const slides = $('.slider_item');
    function nextSlide() {
        slides.eq(currentSlide).removeClass('active');
        currentSlide = (currentSlide + 1) % slides.length;
        slides.eq(currentSlide).addClass('active');
    }
    setInterval(nextSlide, 5000);
});