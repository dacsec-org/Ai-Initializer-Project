import React, { useEffect, useState } from 'react';
import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { ServerHealthService } from 'Frontend/generated/endpoints';

export const config: ViewConfig = { menu: { order: 4, icon: 'line-awesome/svg/file.svg' }, title: 'Metrics' };

export default function MetricsView() {
  const [metrics, setMetrics] = useState<any>(null);

  useEffect(() => {
    const fetchMetrics = async () => {
      const data = await ServerHealthService.getMetrics();
      setMetrics(data);
    };

    fetchMetrics().then(r => r);
  }, []);

  return (
    <div className="flex flex-col h-full items-center justify-center p-l text-center box-border">
      <h1>Server Metrics</h1>
      {metrics ? (
        <pre>{JSON.stringify(metrics, null, 2)}</pre>
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
}
