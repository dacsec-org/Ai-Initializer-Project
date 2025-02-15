import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./ConnectionFactory";
import { MetricsTypes } from '../enums/MetricsTypes';

const SERVICE = "metrics-service"; // Name from @Bridge annotation

/**
 * <h1>{@link MetricsActionBridge}</h1>
 */
export const MetricsActionBridge = (action: MetricsTypes): Observable<any> => {
  return from(
    client.call(
      SERVICE,
      "processMetrics",
      { action })
  ).pipe(map(response => response));
};
