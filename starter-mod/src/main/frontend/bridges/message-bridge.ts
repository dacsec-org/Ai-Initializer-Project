import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./connection-factory";
import { MessageActions } from "../enums/message-actions";

const SERVICE = "messages-service";

/**
 * <h1>{@link MessageBridge}</h1>
 */
export const MessageBridge
  = (action: MessageActions): Observable<any> => {
  return from(
    client.call(
      SERVICE,
      "processMessages",
      { action })
  ).pipe(map(response => response));
};
