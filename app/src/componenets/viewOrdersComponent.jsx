import React from "react";
import { Button, Table } from "react-bootstrap";

const ViewOrdersComponent = (props) => {
  const { status, btnText, orderList, onClick } = props;
  const btnDisabled = btnText === "No Action" ? true : false;
  const btnVariant = btnText === "No Action" ? "secondary" : "info";

  return (
    <div>
      <Table striped>
        <thead>
          <tr>
            <th>Order Id</th>
            <th>Order Date</th>
            <th>Status</th>
            <th>Last Updated At</th>
          </tr>
        </thead>
        <tbody>
          {orderList.map((order) => {
            return (
              <tr key={order.id}>
                <td>{order.id}</td>
                <td>{order.createdAt}</td>
                <td>{status}</td>
                <td>{order.statusUpdatedAt}</td>
                <td>
                  <Button
                    className="mb-1"
                    style={{ width: "100%", color: "white" }}
                    variant={btnVariant}
                    disabled={btnDisabled}
                    onClick={() => {
                      onClick(order.id, order.status);
                    }}
                  >
                    {btnText}
                  </Button>
                </td>
              </tr>
            );
          })}
        </tbody>
      </Table>
    </div>
  );
};

export default ViewOrdersComponent;
