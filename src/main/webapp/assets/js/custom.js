(function ($) {
    "use strict";

    $(window).scroll(function () {
        var scroll = $(window).scrollTop();
        var box = $(".header-text").height();
        var header = $("header").height();

        if (scroll >= box - header) {
            $("header").addClass("background-header");
        } else {
            $("header").removeClass("background-header");
        }
    });

    $(".filters ul li").click(function () {
        $(".filters ul li").removeClass("active");
        $(this).addClass("active");

        var data = $(this).attr("data-filter");
        $grid.isotope({
            filter: data,
        });
    });

    var $grid = $(".grid").isotope({
        itemSelector: ".all",
        percentPosition: true,
        masonry: {
            columnWidth: ".all",
        },
    });

    const Accordion = {
        settings: {
            first_expanded: false,
            toggle: false,
        },

        openAccordion: function (toggle, content) {
            if (content.children.length) {
                toggle.classList.add("is-open");
                let final_height = Math.floor(content.children[0].offsetHeight);
                content.style.height = final_height + "px";
            }
        },

        closeAccordion: function (toggle, content) {
            toggle.classList.remove("is-open");
            content.style.height = 0;
        },

        init: function (el) {
            const _this = this;

            let is_first_expanded = _this.settings.first_expanded;
            if (el.classList.contains("is-first-expanded"))
                is_first_expanded = true;
            let is_toggle = _this.settings.toggle;
            if (el.classList.contains("is-toggle")) is_toggle = true;

            const sections = el.getElementsByClassName("accordion");
            const all_toggles = el.getElementsByClassName("accordion-head");
            const all_contents = el.getElementsByClassName("accordion-body");
            for (let i = 0; i < sections.length; i++) {
                const section = sections[i];
                const toggle = all_toggles[i];
                const content = all_contents[i];

                toggle.addEventListener("click", function (e) {
                    if (!is_toggle) {
                        for (let a = 0; a < all_contents.length; a++) {
                            _this.closeAccordion(all_toggles[a], all_contents[a]);
                        }
                        _this.openAccordion(toggle, content);
                    } else {
                        if (toggle.classList.contains("is-open")) {
                            _this.closeAccordion(toggle, content);
                        } else {
                            _this.openAccordion(toggle, content);
                        }
                    }
                });

                if (i === 0 && is_first_expanded) {
                    _this.openAccordion(toggle, content);
                }
            }
        },
    };

    (function () {
        const accordions = document.getElementsByClassName("accordions");
        for (let i = 0; i < accordions.length; i++) {
            Accordion.init(accordions[i]);
        }
    })();

    (function init() {
        function getTimeRemaining(endtime) {
            var t = Date.parse(endtime) - Date.parse(new Date());
            var seconds = Math.floor((t / 1000) % 60);
            var minutes = Math.floor((t / 1000 / 60) % 60);
            var hours = Math.floor((t / (1000 * 60 * 60)) % 24);
            var days = Math.floor(t / (1000 * 60 * 60 * 24));
            return {
                total: t,
                days: days,
                hours: hours,
                minutes: minutes,
                seconds: seconds,
            };
        }

        function initializeClock(endtime) {
            var timeinterval = setInterval(function () {
                var t = getTimeRemaining(endtime);

                const daysElem = document.querySelector(".days > .value");
                const hoursElem = document.querySelector(".hours > .value");
                const minutesElem = document.querySelector(".minutes > .value");
                const secondsElem = document.querySelector(".seconds > .value");

                if (daysElem) daysElem.innerText = t.days;
                if (hoursElem) hoursElem.innerText = t.hours;
                if (minutesElem) minutesElem.innerText = t.minutes;
                if (secondsElem) secondsElem.innerText = t.seconds;

                if (t.total <= 0) {
                    clearInterval(timeinterval);
                }
            }, 1000);
        }
        initializeClock(new Date().getFullYear() + 1 + "/1/1");
    })();

    // Menu Dropdown Toggle
    if ($(".menu-trigger").length) {
        $(".menu-trigger").on("click", function () {
            $(this).toggleClass("active");
            $(".header-area .nav").slideToggle(200);
        });
    }

    // Page loading animation
    $(window).on("load", function () {
        if ($(".cover").length) {
            $(".cover").parallax({
                imageSrc: $(".cover").data("image"),
                zIndex: "1",
            });
        }

        $("#preloader").animate(
            {
                opacity: "0",
            },
            600,
            function () {
                setTimeout(function () {
                    $("#preloader").css("visibility", "hidden").fadeOut();
                }, 300);
            }
        );
    });
})(window.jQuery);
