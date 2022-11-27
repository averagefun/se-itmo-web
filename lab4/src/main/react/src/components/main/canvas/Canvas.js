import React from 'react';

class Canvas extends React.Component {
    static hatchWidth = 20 / 2;
    static hatchGap = 56;
    static arrowWidth = 10
    static arrowHeight = 15;

    constructor(props) {
        super(props);
        this.canvasRef = React.createRef();
        this.r = props.rDefault;
    }

    render() {
        return (
            <canvas width="300px" height="300px" onClick={event => this.clickCanvasCallback(event)}
                    ref={this.canvasRef}/>
        )
    }

    // after render
    componentDidMount() {
        this.canvas = this.canvasRef.current;

        this.ctx = this.canvas.getContext('2d');
        this.w = this.canvas.width
        this.h = this.canvas.height;

        // draw first time with 'R' label
        this.redrawCanvas('R');
    }

    clickCanvasCallback(event) {
        // process click on canvas
        const canvasLeft = this.canvas.offsetLeft + this.canvas.clientLeft,
            canvasTop = this.canvas.offsetTop + this.canvas.clientTop;

        const x = event.pageX - canvasLeft,
            y = event.pageY - canvasTop;

        const xCenter = Math.round((x - this.w / 2) / (Canvas.hatchGap * (2 / this.r)) * 1000) / 1000,
            yCenter = Math.round((this.h / 2 - y) / (Canvas.hatchGap * (2 / this.r)) * 1000) / 1000;

        this.printDotOnCanvas(xCenter, yCenter, this.r, true);
    }

    printDotOnCanvas(xCenter, yCenter, isHit) {
        this.redrawCanvas(this.r);
        this.ctx.fillStyle = isHit ? '#00ff00' : '#ff0000'
        const x = this.w / 2 + xCenter * Canvas.hatchGap * (2 / this.r) - 3,
            y = this.h / 2 - yCenter * Canvas.hatchGap * (2 / this.r) - 3;
        this.ctx.fillRect(x, y, 6, 6);
    }

    redrawCanvas() {
        this.ctx.clearRect(0, 0, this.w, this.h);

        this.ctx.lineWidth = 2;
        this.ctx.strokeStyle = 'black';

        // y axis
        const drawY = () => {
            this.ctx.beginPath();
            this.ctx.moveTo(this.w / 2, 0);
            this.ctx.lineTo(this.w / 2 - Canvas.arrowWidth, Canvas.arrowHeight);
            this.ctx.moveTo(this.w / 2, 0);
            this.ctx.lineTo(this.w / 2 + Canvas.arrowWidth, Canvas.arrowHeight);
            this.ctx.moveTo(this.w / 2, 0);
            this.ctx.lineTo(this.w / 2, this.h);
            this.ctx.stroke();
            this.ctx.closePath();
        }

        // x axis
        const drawX = () => {
            this.ctx.beginPath();
            this.ctx.moveTo(this.w, this.h / 2);
            this.ctx.lineTo(this.w - Canvas.arrowHeight, this.h / 2 - Canvas.arrowWidth);
            this.ctx.moveTo(this.w, this.h / 2);
            this.ctx.lineTo(this.w - 15, this.h / 2 + Canvas.arrowWidth);
            this.ctx.moveTo(this.w, this.h / 2);
            this.ctx.lineTo(0, this.h / 2);
            this.ctx.stroke();
            this.ctx.closePath();
        }

        const drawHatches = (n) => {
            this.ctx.beginPath();
            for (let i = 1; i <= n; i++) {
                this.ctx.moveTo(this.w / 2 - Canvas.hatchWidth, this.h / 2 - Canvas.hatchGap * i);
                this.ctx.lineTo(this.w / 2 + Canvas.hatchWidth, this.h / 2 - Canvas.hatchGap * i);
                this.ctx.moveTo(this.w / 2 - Canvas.hatchWidth, this.h / 2 + Canvas.hatchGap * i);
                this.ctx.lineTo(this.w / 2 + Canvas.hatchWidth, this.h / 2 + Canvas.hatchGap * i);
                this.ctx.moveTo(this.w / 2 - Canvas.hatchGap * i, this.h / 2 - Canvas.hatchWidth);
                this.ctx.lineTo(this.w / 2 - Canvas.hatchGap * i, this.h / 2 + Canvas.hatchWidth);
                this.ctx.moveTo(this.w / 2 + Canvas.hatchGap * i, this.h / 2 - Canvas.hatchWidth);
                this.ctx.lineTo(this.w / 2 + Canvas.hatchGap * i, this.h / 2 + Canvas.hatchWidth);
            }
            this.ctx.stroke();
            this.ctx.closePath();
        }

        const drawMainFigure = () => {
            this.ctx.fillStyle = '#f13a2355';
            this.ctx.beginPath();
            this.ctx.moveTo(this.w / 2 + Canvas.hatchGap, this.h / 2);
            this.ctx.lineTo(this.w / 2 + Canvas.hatchGap * 2, this.h / 2);
            this.ctx.lineTo(this.w / 2 + Canvas.hatchGap * 2, this.h / 2 + Canvas.hatchGap);
            this.ctx.lineTo(this.w / 2, this.h / 2 + Canvas.hatchGap);
            this.ctx.lineTo(this.w / 2, this.h / 2);
            this.ctx.lineTo(this.w / 2 - Canvas.hatchGap, this.h / 2);
            this.ctx.arc(this.w / 2, this.h / 2, Canvas.hatchGap, Math.PI, 1.5 * Math.PI, false);
            this.ctx.lineTo(this.w / 2, this.h / 2 - Canvas.hatchGap * 2);
            this.ctx.lineTo(this.w / 2 + Canvas.hatchGap, this.h / 2);
            this.ctx.fill();
            this.ctx.strokeStyle = '#f13a23'
            this.ctx.stroke();
            this.ctx.closePath();
        }

        drawY();
        drawX();
        drawHatches(2);
        drawMainFigure();

        const fontSize = Canvas.hatchGap / 3.5
        this.ctx.fillStyle = 'black'

        this.ctx.font = `500 ${fontSize * 1.4}px Roboto`;
        this.ctx.fillText('y', this.w / 2 - Canvas.hatchWidth * 2.8, 15)
        this.ctx.fillText('x', this.w - 20, this.h / 2 - Canvas.hatchWidth * 2.4)

        let label1, label2;
        if (isNaN(this.r)) {
            label1 = this.r + '/2'
            label2 = this.r
        } else {
            label1 = this.r / 2
            label2 = this.r
        }

        this.ctx.font = `800 ${fontSize}px Roboto`;
        this.ctx.fillText(label1, this.w / 2 + Canvas.hatchGap - 3, this.h / 2 + Canvas.hatchWidth * 2.8);
        this.ctx.fillText(label2, this.w / 2 + Canvas.hatchGap * 2 - 3, this.h / 2 + Canvas.hatchWidth * 2.8);
        this.ctx.fillText('-' + label1, this.w / 2 - Canvas.hatchGap - 7, this.h / 2 + Canvas.hatchWidth * 2.8);
        this.ctx.fillText('-' + label2, this.w / 2 - Canvas.hatchGap * 2 - 7, this.h / 2 + Canvas.hatchWidth * 2.8);

        this.ctx.fillText(label1, this.w / 2 + Canvas.hatchWidth * 2, this.h / 2 - Canvas.hatchGap + 3);
        this.ctx.fillText(label2, this.w / 2 + Canvas.hatchWidth * 2, this.h / 2 - Canvas.hatchGap * 2 + 3);
        this.ctx.fillText('-' + label1, this.w / 2 + Canvas.hatchWidth * 2, this.h / 2 + Canvas.hatchGap + 3);
        this.ctx.fillText('-' + label2, this.w / 2 + Canvas.hatchWidth * 2, this.h / 2 + Canvas.hatchGap * 2 + 3);
    }
}

export default Canvas;