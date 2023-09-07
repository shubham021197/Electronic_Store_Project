import { Container, Button, Row, Col } from "react-bootstrap";
import ViewOrdersComponent from "../componenets/viewOrdersComponent";
import axios from "axios";
import React, { useEffect, useState } from "react";

import { toast } from "react-toastify";
import config from "../config/serverUrl";


const ViewOrders = () => {
  const [orderList, setOrderList] = useState([]);
  // const [status, setStatus] = useState('DELIVERED')
  const [orderStatus, setOrderStatus] = useState("DELIVERED");
  const [btnText, setBtnText] = useState("No Action");

  const loadOrders = (status) => {
    // console.log(sessionStorage['jwt'])
    axios
      .get(config.serverUrl+"admin/getOrderDetails/" + status)
      .then((response) => {
        const result = response.data;
        //console.log('RESULT' + result)
        if (result.message === "success") {
          // console.log(result.data)
          setOrderList(result.data);
        } else {
          toast.error(result.error);
        }
      })
      .catch((error) => {
        console.log("error");
        console.log(error);
      });
  };

  const changeStatus = (orderId, status) => {
    const stat2Change = status === "ORDERED" ? "INTRANSIT" : "DELIVERED";
    console.log(orderId + " " + stat2Change);

    axios
      .put(
        config.serverUrl+ "admin/orders/updateStatus",
        {
          id: orderId,
          status: stat2Change,
        }
      )
      .then((response) => {
        const result = response.data;
        //console.log('RESULT' + result)
        if (result.message === "success") {
          //console.log(result.data)
        } else {
          toast.error(result.data);
        }
      })
      .catch((error) => {
        console.log("error");
        console.log(error);
      });

    loadOrders(status);
    console.log(orderList);
    //setStatus(status)
  };

  useEffect(() => {
    loadOrders(orderStatus);
  }, [orderList]);

  const showOrders = (status) => {
    //console.log(status)
    setOrderStatus(status);
    if (status === "ORDERED") {
      setBtnText("Change to In-Transit");
    } else if (status === "INTRANSIT") {
      setBtnText("Change to Delivered");
    } else {
      setBtnText("No Action");
    }
    loadOrders(status);
  };
  return (
    <>
      <Container>
        <h3 style={{ marginTop: "20px" }}>View Orders</h3>
        <hr></hr>
        <Row md={12}>
          <Col md={3}>
            <Button
              onClick={() => {
                showOrders("DELIVERED");
              }}
              style={{ color: "white", width: "80%" }}
              variant="success"
            >
              Delivered
            </Button>
          </Col>
          <Col md={3}>
            <Button
              onClick={() => {
                showOrders("ORDERED");
              }}
              style={{ color: "white", width: "80%" }}
              variant="info"
            >
              Ordered
            </Button>
          </Col>
          <Col md={3}>
            <Button
              onClick={() => {
                showOrders("INTRANSIT");
              }}
              style={{ color: "white", width: "80%" }}
              variant="warning"
            >
              In- Transit
            </Button>
          </Col>

          <Col md={3}>
            <Button
              onClick={() => {
                showOrders("CANCELLED");
              }}
              style={{ color: "white", width: "80%" }}
              variant="danger"
            >
              Cancelled
            </Button>
          </Col>
        </Row>
        <br />
        <br />
        <Row>
          <ViewOrdersComponent
            status={orderStatus}
            btnText={btnText}
            orderList={orderList}
            onClick={(id, status) => {
              console.log("func called " + id + " " + status);
              changeStatus(id, status);
            }}
          />
        </Row>
      </Container>
    </>
  );
};

export default ViewOrders;
