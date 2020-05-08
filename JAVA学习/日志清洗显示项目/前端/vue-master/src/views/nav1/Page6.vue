<template>
    <div class="button" @click="wavesEffect">
        <p style="font-family:verdana,serif;font-size:150%;color:green">确认</p>
        <div class="wavesbtn" ref="wavesbtn"></div>
    </div>
</template>
<script>
    export default {
        methods: {
            wavesEffect(e) {
                e = e || window.event;
                let position = e.target.getBoundingClientRect();
                let doc = document.documentElement;
                let div = document.createElement("div");
                div.className = "effect";
                this.$refs.wavesbtn.appendChild(div);

                let top = e.pageY - (position.top + window.pageYOffset) - doc.clientTop;
                let left =
                    e.pageX - (position.left + window.pageXOffset) - doc.clientLeft;
                let dur = 750;
                div.style = `
        left:${left}px;
        top:${top}px;
        transform:scale(20);
        transition-duration: 1s;
        transition-timing-function: cubic-bezier(0.25, 0.46, 0.45, 0.94);`;
                setTimeout(() => {
                    div.style = `
        transition-duration: 1s;
        transition-timing-function: cubic-bezier(0.25, 0.46, 0.45, 0.94);
        opacity:0;
        left:${left}px;
        top:${top}px;
        transform:scale(20);`;
                    setTimeout(() => {
                        this.$refs.wavesbtn.removeChild(div);
                    }, dur);
                }, 100);
            }
        }
    };
</script>
<style scoped>
    .button {
        width: 150px;
        height: 50px;
        border-radius: 20px;
        line-height: 50px;
        text-align: center;
        border: 1px solid red;
        position: relative;
        overflow: hidden;
    }
    p {
        width: 100%;
        line-height: 10px;
        position: absolute;
        left: 0;
        top: 0;
        z-index: 1;
    }

    .wavesbtn {
        width: 100%;
        height: 100%;
        position: absolute;
        left: 0;
        top: 0;
    }
</style>
<style>
    .effect {
        position: absolute;
        width: 20px;
        height: 20px;
        border-radius: 50%;
        transition: all 0.7s ease-out;
        background: #00ff00;
        transform: scale(0);
        transition-property: transform, opacity, -webkit-transform;
        opacity: 1;
        pointer-events: none;
    }

</style>
