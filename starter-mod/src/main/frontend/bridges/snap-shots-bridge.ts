import { from, Observable } from "rxjs";
import { map } from "rxjs/operators";
import client from "./ConnectionFactory";
import { SnapShotsActions } from '../enums/SnapShotsActions';

const SERVICE = "snapshots-service"; // Name from @Bridge annotation

/**
 * <h1>{@link SnapshotsBridge}</h1>
 */
export const SnapshotsBridge = (action: SnapShotsActions): Observable<any> => {
  return from(
    client.call(SERVICE, "processSnapshots", { action })
  ).pipe(map(response => response));
}
