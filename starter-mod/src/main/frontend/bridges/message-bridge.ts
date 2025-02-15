import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./ConnectionFactory";
import { MessageAction } from "../enums/MessageAction";

const SERVICE = "messages-service";

/**
 * <h1>{@link MessageBridge}</h1>
 * @param action
 * @constructor
 */
export const MessageBridge
  = (action: MessageAction): Observable<any> => {
  return from(
    client.call(
      SERVICE,
      "processMessages",
      { action })
  ).pipe(map(response => response));
};
