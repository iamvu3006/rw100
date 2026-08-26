import React, { useState } from 'react';

function DemoHook(props) {
    // Khai báo State
    const [count1, setCount1] = useState(0);

    //Hàm xử lý sự kiện khi nhấn nút
    let handleClick1 = () => {
        setCount1(count1 + 1);
    }
    return (
        <>
            <div class="row">
                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <button type="button" class="btn btn-danger" onClick={handleClick1}>click</button>
                </div>

                <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                    <h3>Bạn đã click {count1} lần</h3>
                </div>
            </div>
        </>
    );
}

export default DemoHook;
